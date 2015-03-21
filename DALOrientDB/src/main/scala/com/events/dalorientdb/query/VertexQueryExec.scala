package com.events.dalorientdb.query

import scala.concurrent.Promise
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import com.tinkerpop.blueprints.impls.orient.OrientDynaElementIterable
import com.orientechnologies.orient.core.sql.query._
import com.tinkerpop.blueprints.impls.orient._

/**
 * @author pedro
 */
class VertexQueryExec[T](
  val graph: OrientGraph) extends YieldingQueryExec[OrientVertex, T] 
{
  def exec(sql: String, converter: ResultConverter[OrientVertex, T]) : Future[Traversable[T]] = {
    Future{
      val query : OSQLSynchQuery[OrientVertex] = new OSQLSynchQuery[OrientVertex](sql)
      val req = graph.command(query)
      val iterable : OrientDynaElementIterable = req.execute()
      convertResults(iterable, converter)
    }
  }
  
  private def convertResults(iterable: OrientDynaElementIterable, converter: ResultConverter[OrientVertex, T]) : Traversable[T] = {
    var results = List[T]()
    val iterator = iterable.iterator()
    while(iterator.hasNext()){
      val result = iterator.next.asInstanceOf[OrientVertex]
      val converted = converter.convert(result)
      results = converted :: results
    }
    results
  }
}