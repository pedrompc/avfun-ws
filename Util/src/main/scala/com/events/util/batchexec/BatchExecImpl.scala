package com.events.util.batchexec

/**
 * @author pedro
 */
class BatchExecImpl(retries: Int) extends BatchExec{
  def exec(action: () => Unit) : Unit = {
    retry[Unit](retries, action)
  }
  
  def exec[T](action: () => T) : T = {
    return retry[T](retries, action)
  }
  
  def retry[T](retries: Int, action: () => T) : T = {
    var lastException : Throwable = null
    for(i <- 0 to retries){
      try{
        return action();  
      }
      catch{
        case ex: RetryException => {
          lastException = ex.getCause()
        }
        case default : Throwable => throw default
      }
    }
    
    throw lastException
  }
}