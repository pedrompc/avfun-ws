package com.events.dalorientdb.sql.dsl

import com.events.dalorientdb.query.Templates
import com.events.dalorientdb.sql.{ValueStatement, UnaryConditionStatement, ConditionStatement, SQLStatement}

/**
 * Created by pedro on 05-04-2015.
 */
object condEq {
  def apply(leftSide: String, rightSide: SQLStatement) : ConditionStatement = {
    new UnaryConditionStatement(new ValueStatement[String](Templates.getEqualsFilter(leftSide, rightSide.eval())))
  }
}
