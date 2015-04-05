package com.events.dalorientdb.event

import com.events.dalorientdb.timeseries.TimeSeriesQueries
import com.events.domainmodel.event.{ Duration => DMDuration }
import com.events.dalorientdb.timeseries.TimeSeriesOperations
import com.github.nscala_time.time.Imports._
import com.events.dalorientdb.Classes
import com.events.dalorientdb.query._
import com.events.dalorientdb.sql.dsl._
import com.events.domainmodel.event.EventSearch
import com.events.dalorientdb.sql.AndConditionStatement

/**
 * @author pedro
 */
class EventSearchQuery (
  val searchTerms: EventSearch)
{ 
  def getQuery() : String = {
    var components = new QueryComponents(List("*"), Classes.event, List())
    components = applyDates(searchTerms.startDate, searchTerms.endDate, components)
    components = applyName(searchTerms.name, components)
    //components = applyName(searchTerms., components)
    
    select(components.filters).eval()
  }
  
  def applyName(name: Option[String], components: QueryComponents) : QueryComponents = {
    name.map { someName => 
        val nameFilter = Templates.getEqualsFilter("name", someName)
        components.addFilter(nameFilter)
    }.getOrElse(components)
  }
  
  def applyDates(startDate: DateTime, endDate: DateTime, components: QueryComponents) : QueryComponents = {
    val eventDates = TimeSeriesOperations.getEventDates(List(new DMDuration(startDate, endDate)))
    TimeSeriesQueries.getDaysQuery(eventDates.getDays())
    null
  }
}