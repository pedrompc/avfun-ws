package com.events.dalorientdb.sql

/**
 * Created by pedro on 05-04-2015.
 */
class ParenthesisStatement(
    val value: SQLStatement) extends SQLStatement
{
  val template = "(%s)"

  override def eval() : String = {
    template.format(value.eval())
  }
}
