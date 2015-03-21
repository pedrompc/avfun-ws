package com.events.util.promiseexec

import scala.concurrent.Promise
import scala.concurrent.Future

/**
 * @author pedro
 */
object PromiseExec {import com.events.util.promiseexec.PromiseTry

  def apply[T](body: Promise[T] => Unit) : Future[T] = {
    val promise = Promise[T]
    val future = promise.future
    PromiseTry(promise) {
      body(promise)
    }
    future
  }
}