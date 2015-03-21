package com.events.util.batchexec

import scala.concurrent.Promise
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.collection.immutable.Queue

/**
 * @author pedro
 */
class AsyncBatchExecImpl(
    var retries: Int) extends AsyncBatchExec
{
  override def exec[T](action: (AsyncBatchExecContext[T] => Unit)) : Future[T] = {
    val promise = Promise[T]
    val future = promise.future
    execWithRetries[T](promise, action, retries)
    future
  }
  
  private def execWithRetries[T](promise: Promise[T], action: (AsyncBatchExecContext[T] => Unit), retries: Int){
    execTry(promise, action, 0, retries)
  }
  
  private def isRetryException(cause: Throwable) : Boolean = {
    cause match {
      case ex: RetryException => {
        return true
      }
      case default : Throwable => return false
    } 
  }
  
  private def execTry[T](promise: Promise[T], action: (AsyncBatchExecContext[T] => Unit), currRetry: Int, maxRetries: Int, cause: Throwable = null){
    if(currRetry > maxRetries){
      promise.failure(cause)
      return
    }
    
    val onSuccess = (context: AsyncBatchExecContext[T], result: T) => {
      context.closeCloseables()
      promise.success(result)
      return
    }
    
    val onFailure : (AsyncBatchExecContext[T], Throwable) => Unit = 
      (context, cause) => {
        context.closeCloseables()
        
        if(isRetryException(cause)) {
          execTry(promise, action, currRetry + 1, maxRetries, cause.getCause)
        }
        else  {
          promise.failure(cause)
        }
    }
    
    val context : AsyncBatchExecContextImpl[T] = new AsyncBatchExecContextImpl[T](onSuccess, onFailure)
    action(context)
  }
}