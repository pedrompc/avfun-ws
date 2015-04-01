package com.events.dalorientdb.sql

/**
 * @author pedro
 */
abstract class BinaryConditionStatement(
    protected val leftCondition: ConditionStatement,
    protected val rightCondition: ConditionStatement) extends ConditionStatement
{ 
  protected val template = " %s %s %s "
  
  protected def getKeyword() : String
  
  override def eval(): String = {
    template.format(leftCondition.eval().trim(), getKeyword(), rightCondition.eval().trim())
  }
}