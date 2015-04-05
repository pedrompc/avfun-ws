package com.events.dalorientdb.sql.dsl

import com.events.dalorientdb.sql._

/**
 * @author pedro
 */
trait ComposableWhere { 
  def where(fromStatement: FromStatement, conditions: Traversable[ConditionStatement]) : WhereStatementDSL = {
    val composedConditions = Helper.composeConditions(conditions)
    new WhereStatementDSL(fromStatement, composedConditions)
  }
}