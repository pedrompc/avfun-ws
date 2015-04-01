package com.events.dalorientdb.sql

/**
 * @author pedro
 */
class OrConditionStatement(
    override val leftCondition: ConditionStatement,
    override val rightCondition: ConditionStatement) extends BinaryConditionStatement(leftCondition, rightCondition)
{ 
  override def getKeyword() : String = "or"
}