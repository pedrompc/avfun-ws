package com.events.dalorientdb.sql

/**
 * Created by pedro on 05-04-2015.
 */
class StringStatement(
  override val value: String) extends ValueStatement[String](value)
{
  val template = "\'%s\'"

  override def eval() : String = {
    template.format(super.eval())
  }
}
