package com.events.dalorientdb.query

/**
 * @author pedro
 */
trait WhereClause extends SelectQuery {
  val whereTemplate = "%s where %s"
  
  def getClauses() : String
  
  abstract override def getQuery() : String = {
    val prevQuery = super.getQuery()
    whereTemplate.format(prevQuery, getClauses())  
  }
}