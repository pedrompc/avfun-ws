package com.events.util.batchexec

import scala.concurrent.Future
import scala.concurrent.Promise
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * @author pedro
 */
abstract class AsyncBatchExec {
  def exec[T](action: (AsyncBatchExecContext[T] => Unit)) : Future[T]
}