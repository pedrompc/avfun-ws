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

  def composeConditions(conditions: Traversable[ConditionStatement]) : ConditionStatement = {
    if(conditions.isEmpty){
      return new UnaryConditionStatement(str(""))
    }

    conditions.reduceLeft((c1, c2) => { new AndConditionStatement(c1, c2) })
  }
}