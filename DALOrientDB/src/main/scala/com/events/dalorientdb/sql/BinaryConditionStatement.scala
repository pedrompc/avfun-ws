package com.events.dalorientdb.sql

/**
 * @author pedro
 */
abstract class BinaryConditionStatement(
    val leftCondition: ConditionStatement,
    val rightCondition: ConditionStatement) extends ConditionStatement
{ 
  val template = " %s %s %s "
  
  def getKeyword() : String
  
  override def eval(): String = {
    template.format(leftCondition.eval().trim(), getKeyword(), rightCondition.eval().trim())
  }
}