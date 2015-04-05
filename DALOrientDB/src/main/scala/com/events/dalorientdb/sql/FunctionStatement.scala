package com.events.dalorientdb.sql

/**
 * @author pedro
 */
class FunctionStatement(
    protected val name: String,
    protected val values: Traversable[SQLStatement]) extends SQLStatement
{
  protected val valueSeparator = ","
  val template = "%s(%s)"
  
  def eval() : String = {
    val evaledValues = values.map { _.eval() }
    template.format(name, evaledValues.mkString(valueSeparator).trim())
  } 
}