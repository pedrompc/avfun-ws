package com.events.dalorientdb.query

import scala.concurrent.Future

/**
 * @author pedro
 */
trait YieldingQueryExec[O] {
  def exec[T](query: String, converter: ResultConverter[O, T]) : Future[Traversable[T]] 
}