package com.events.util.batchexec

abstract class AsyncBatchExecContext[T] { 
  def success(result: T) : Unit
  
  def failure(cause: Throwable) : Unit
  
  def enlistCloseable(closeable: {def close() : Unit}): Unit
  
  def closeCloseables(): Unit
}