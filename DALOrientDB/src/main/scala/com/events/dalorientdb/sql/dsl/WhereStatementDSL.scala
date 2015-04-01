package com.events.dalorientdb.sql.dsl

import com.events.dalorientdb.sql._

/**
 * @author pedro
 */
class WhereStatementDSL(
    override val fromStatement: FromStatement,
    override val conditionStatement: ConditionStatement) extends WhereStatement(fromStatement, conditionStatement) with ComposableWhere
{
  def where(conditions: String*) : WhereStatementDSL = {
    val conditionStatements = Helper.mkConditions(conditions)
    where(fromStatement, conditionStatement :: conditionStatements)
  }
}