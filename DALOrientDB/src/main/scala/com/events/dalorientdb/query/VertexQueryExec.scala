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
class VertexQueryExec[OrientVertex](
  val graph: OrientGraph) extends YieldingQueryExec[OrientVertex]
{
  def exec(sql: String) : Future[Traversable[OrientVertex]] = {
    // dummy converter
    exec(sql, new ResultConverter[OrientVertex, OrientVertex]{
       override def convert(convertable: OrientVertex) : OrientVertex = convertable
    })
  }
  
  def exec[T](sql: String, converter: ResultConverter[OrientVertex, T]) : Future[Traversable[T]] = {
    Future{
      val query : OSQLSynchQuery[OrientVertex] = new OSQLSynchQuery[OrientVertex](sql)
      val req = graph.command(query)
      val iterable : OrientDynaElementIterable = req.execute()
      convertResults[T](iterable, converter)
    }
  }
  
  private def convertResults[T](iterable: OrientDynaElementIterable, converter: ResultConverter[OrientVertex, T]) : Traversable[T] = {
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