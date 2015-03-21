package com.events.util.batchexec

import scala.concurrent.Promise
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.collection.immutable.Queue

/*
 * TODO: the context definition is pretty fucked up right now, the onSucess and onFailure should work like the Future ones, but i haven't figured it out yet
 * */

/**
 * @author pedro
 */
class AsyncBatchExecContextImpl[T](
      val onSuccess: (AsyncBatchExecContext[T], T) => Unit,
      val onFailure: (AsyncBatchExecContext[T], Throwable) => Unit) extends AsyncBatchExecContext[T]
{
  var closeables: Option[Queue[Closeable]] = None
  
  def enlistCloseable(closeable: {def close() : Unit}): Unit = {
    // TODO: get rid of synchronized
    synchronized{
      if(!closeables.isDefined){
        closeables = Some(Queue[Closeable]())
      }
      else{
        closeables = Some(closeables.get.enqueue(closeable))
      }
    }
  }
  
  def closeCloseables(): Unit = {
    closeables.map{ someCloseables => 
      for(closeable <- someCloseables){
        closeable.close()
      }
    }
  }
  
  def success(result: T): Unit = {
    onSuccess(this, result)
  }
  
  def failure(cause: Throwable): Unit = {
    onFailure(this, cause)
  }
}