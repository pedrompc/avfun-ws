package com.events.dalorientdb.sql

/**
 * @author pedro
 */
class FromStatement (
    protected val selectStatement: SelectStatement,
    protected val fromTarget: SQLStatement) extends SQLStatement 
{
  protected val statementTemplate = "%s from %s"
  
  def eval() : String = {
    statementTemplate.format(selectStatement.eval().trim(), fromTarget.eval().trim())
  }
  
  def where(condition: ConditionStatement) : WhereStatement = new WhereStatement(this, condition)
}