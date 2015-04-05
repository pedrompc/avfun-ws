package com.events.dalorientdb.sql.dsl

import com.events.dalorientdb.sql.{ValueStatement, UnaryConditionStatement, ConditionStatement}

/**
 * Created by pedro on 05-04-2015.
 */
object cond {
  def apply(cond: String) : ConditionStatement = {
    new UnaryConditionStatement(new ValueStatement[String](cond))
  }
}
