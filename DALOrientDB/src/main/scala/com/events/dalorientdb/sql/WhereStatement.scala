package com.events.dalorientdb.sql

/**
 * @author pedro
 */
class WhereStatement(
    val fromStatement: FromStatement,
    val conditionStatement: ConditionStatement) extends SQLStatement 
{
  val whereTemplate = "%s where %s"
  
  def eval() : String = {
    whereTemplate.format(fromStatement.eval().trim(), conditionStatement.eval().trim())
  }
  
  def where(condition: ConditionStatement) : WhereStatement = {
    val composedConditions = new AndConditionStatement(conditionStatement, condition)
    new WhereStatement(fromStatement, composedConditions)
  } 
}