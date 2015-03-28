package com.events.dalorientdb.event

import com.events.dalorientdb.query._
import com.events.domainmodel.event.EventSearch
import com.events.dalorientdb.spatial.SpatialWhereClause

/**
 * @author pedro
 */
class EventSelectQuery(
    val searchTerms: EventSearch) extends SelectQuery
{
  def getProjections() : String = {
    ""
  }
  
  def getFrom() : String = {
    ""
  }
}