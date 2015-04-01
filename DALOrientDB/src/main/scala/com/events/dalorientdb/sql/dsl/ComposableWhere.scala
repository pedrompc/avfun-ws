package com.events.dalorientdb.sql.dsl

import com.events.dalorientdb.sql._

/**
 * @author pedro
 */
trait ComposableWhere { 
  def where(fromStatement: FromStatement, conditions: Traversable[ConditionStatement]) : WhereStatementDSL = {
    val statementConditions = conditions.reduceLeft((x, y) => new AndConditionStatement(x, y))
    new WhereStatementDSL(fromStatement, statementConditions)
  }
}