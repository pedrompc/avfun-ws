package com.events.dalorientdb.sql

/**
 * @author pedro
 */
class ValueStatement[T](
    protected val value: T) extends SQLStatement
{
  def eval() : String = {
    value.toString().trim()
  }
}