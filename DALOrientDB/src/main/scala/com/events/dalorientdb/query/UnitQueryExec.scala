package com.events.dalorientdb.query

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import com.tinkerpop.blueprints.impls.orient._
import com.orientechnologies.orient.core.sql._

/**
 * @author pedro
 */
class UnitQueryExec(graph: OrientGraph) extends NonYieldingQueryExec{
  def exec(sql: String) : Future[Unit] = {
    Future{
      val command = graph.command(new OCommandSQL(sql))
      command.execute()
    }
  }
}