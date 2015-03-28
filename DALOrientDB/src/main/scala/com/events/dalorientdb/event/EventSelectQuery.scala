package com.events.dalorientdb.event

import com.events.dalorientdb.query._
import com.events.domainmodel.event.EventSearchTerms

/**
 * @author pedro
 */
class EventSelectQuery(
    val searchTerms: EventSearchTerms) extends SelectQuery
{
  def getProjections() : String = {
    ""
  }
  
  def getFrom() : String = {
    ""
  }
}