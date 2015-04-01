package com.events.dalorientdb.sql.dsl

import com.events.dalorientdb.sql._

/**
 * @author pedro
 */
object Helper {
  def mkConditions(conditions: Traversable[String]) : List[ConditionStatement] = {
    val conditionStatements : List[ConditionStatement] = conditions.map { c => new UnaryConditionStatement(new ValueStatement[String](c)) }.toList
    conditionStatements
  }
}