package com.events.dalorientdb.query

import scala.concurrent.Future

/**
 * @author pedro
 */
trait YieldingQueryExec[O, T] {
  def exec(query: String, converter: ResultConverter[O, T]) : Future[Traversable[T]] 
}