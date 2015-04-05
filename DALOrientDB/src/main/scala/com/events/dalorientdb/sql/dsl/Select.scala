package com.events.dalorientdb.sql.dsl

import com.events.dalorientdb.sql._

/**
 * @author pedro
 */
object select {
  
  def apply(projections: String*) : SelectStatementDSL = {
    val statementProjections = projections.map { new ValueStatement[String](_) }
    new SelectStatementDSL(statementProjections)
  }
  
  def apply(projections: List[String]) : SelectStatementDSL = {
    val statementProjections = projections.map { new ValueStatement[String](_) }
    new SelectStatementDSL(statementProjections)
  }
  
  def apply(statement: SQLStatement) : SelectStatementDSL = {
    new SelectStatementDSL(List(statement))
  }

  def apply(statements: Traversable[SQLStatement]) : SelectStatementDSL = {
    new SelectStatementDSL(statements)
  }
}