package com.events.dalorientdb.event

import com.events.dalorientdb.spatial.SpatialWhereClause
import com.events.dalorientdb.query._
import com.events.domainmodel.event.EventSearch

/**
 * @author pedro
 */
class EventSearchQuery (
  val searchTerms: EventSearch) extends Query
{ 
  override def getQuery() : String = {
    val query = new EventSelectQuery(searchTerms) with SpatialWhereClause
    query.getQuery()
  }
}