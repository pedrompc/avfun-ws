package com.events.util.promiseexec

import scala.concurrent.Promise

/**
 * @author pedro
 */
object PromiseTry {
  def apply[T](promise: Promise[T])(body: => Unit) {
    try{
      body
    }
    catch {
      case cause : Exception => promise.failure(cause)
    }
  }
}