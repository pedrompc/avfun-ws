package com.events.dalorientdb.query

import com.events.dalorientdb.Classes

/**
 * @author pedro
 */
class QueryComponents(
    val projections: List[String],
    val target: String,
    val filters: List[String]) 
{  
  def addProjection(projection: String) : QueryComponents = {
    new QueryComponents(projections :+ projection, this.target, this.filters)
  }
  
  def addProjections(moreProjections: List[String]) : QueryComponents = {
    new QueryComponents(projections ::: moreProjections, this.target, this.filters)
  }
  
  def addFilter(filter: String) : QueryComponents = {
    new QueryComponents(this.projections, this.target, filters :+ filter)
  }
  
  def addFilters(moreFilters: List[String]) : QueryComponents = {
    new QueryComponents(this.projections, this.target, filters ::: moreFilters)
  }
}