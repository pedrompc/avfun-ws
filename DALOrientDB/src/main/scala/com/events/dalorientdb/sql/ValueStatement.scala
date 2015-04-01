package com.events.dalorientdb.sql

/**
 * @author pedro
 */
class ValueStatement[T](
    val value: T) extends SQLStatement
{
  def eval() : String = {
    val x = value.toString();
    value.toString().trim()
  }
}