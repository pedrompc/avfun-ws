package com.events.dalorientdb.query

import com.events.dalorientdb.Classes
import com.events.dalorientdb.sql.{ConditionStatement, SQLStatement}

/**
 * @author pedro
 */
class QueryComponents(
    val projections: List[SQLStatement],
    val target: SQLStatement,
    val filters: List[ConditionStatement])
{
  def setTarget(target: SQLStatement) : QueryComponents = {
    new QueryComponents(this.projections, target, this.filters)
  }

  def addProjection(projection: SQLStatement) : QueryComponents = {
    new QueryComponents(projections :+ projection, this.target, this.filters)
  }
  
  def addProjections(moreProjections: List[SQLStatement]) : QueryComponents = {
    new QueryComponents(projections ::: moreProjections, this.target, this.filters)
  }
  
  def addFilter(filter: ConditionStatement) : QueryComponents = {
    new QueryComponents(this.projections, this.target, filters :+ filter)
  }
  
  def addFilters(moreFilters: List[ConditionStatement]) : QueryComponents = {
    new QueryComponents(this.projections, this.target, filters ::: moreFilters)
  }
}