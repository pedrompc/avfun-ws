package com.events.dalorientdb.sql

/**
 * @author pedro
 */
abstract class ConditionStatement extends SQLStatement
{    
  def and(condition: ConditionStatement) : ConditionStatement = new AndConditionStatement(this, condition)
  
  def or(condition: ConditionStatement) : ConditionStatement = new OrConditionStatement(this, condition)
}