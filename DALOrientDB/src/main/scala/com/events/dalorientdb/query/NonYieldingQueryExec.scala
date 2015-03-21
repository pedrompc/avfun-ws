package com.events.dalorientdb.query

import scala.concurrent.Future

/**
 * @author pedro
 */
trait NonYieldingQueryExec {
  def exec(query: String) : Future[Unit]
}