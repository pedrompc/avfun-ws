package com.events.dalorientdb.sql.dsl

import com.events.dalorientdb.sql._

/**
 * @author pedro
 */
class SelectStatementDSL(
    override val projections: Traversable[SQLStatement]) extends SelectStatement(projections)
{  
  def from(fromTarget: String) : FromStatementDSL = new FromStatementDSL(this, new ValueStatement[String](fromTarget))
}