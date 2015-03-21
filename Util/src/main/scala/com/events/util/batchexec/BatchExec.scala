package com.events.util.batchexec

/**
 * @author pedro
 */
abstract class BatchExec {
  def exec(action: () => Unit) : Unit
  def exec[T](action: () => T) : T
}