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
}