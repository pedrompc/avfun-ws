package com.events.dalorientdb.sql

/**
 * @author pedro
 */
class FromStatement (
    val selectStatement: SelectStatement,
    val fromTarget: SQLStatement) extends SQLStatement 
{
  val statementTemplate = "%s from %s"
  
  def eval() : String = {
    statementTemplate.format(selectStatement.eval().trim(), fromTarget.eval().trim())
  }
  
  def where(condition: ConditionStatement) : WhereStatement = new WhereStatement(this, condition)
}