package com.events.dalorientdb.sql.dsl

import com.events.dalorientdb.sql._

/**
 * @author pedro
 */
class FromStatementDSL (
    override val selectStatement: SelectStatement,
    override val fromTarget: SQLStatement) extends FromStatement(selectStatement, fromTarget) with ComposableWhere
{
  def where(conditions: String*) : WhereStatementDSL = {
    val conditionStatements = Helper.mkConditions(conditions)
    where(this, conditionStatements)
  }
}