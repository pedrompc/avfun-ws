package com.events.dalorientdb.event

import com.events.dalorientdb.timeseries.TimeSeriesQueries
import com.events.domainmodel.event.{ Duration => DMDuration }
import com.events.dalorientdb.timeseries.TimeSeriesOperations
import com.github.nscala_time.time.Imports._
import com.events.dalorientdb.{Functions, Classes}
import com.events.dalorientdb.query._
import com.events.dalorientdb.sql.SQLStatement
import com.events.dalorientdb.sql.dsl._
import com.events.domainmodel.event.EventSearch

/**
 * @author pedro
 */
class EventSearchQuery (
  val searchTerms: EventSearch)
{ 
  def getQuery() : SQLStatement = {
    var components = new QueryComponents(List(), value(Classes.event), List())
    components = applyDates(searchTerms, components)
    components = applyName(searchTerms, components)
    getComposedQuery(components)
  }

  private def getComposedQuery(components: QueryComponents) : SQLStatement = {
    var query : SQLStatement = select(components.projections)

    if(components.target != null) {
      query = query match {
        case selectQuery: SelectStatementDSL => selectQuery.from(components.target)
      }
    }

    if(!components.filters.isEmpty) {
      query = query match {
        case fromQuery: FromStatementDSL => fromQuery.where(components.filters)
      }
    }

    query
  }
  
  private def applyName(search: EventSearch, components: QueryComponents) : QueryComponents = {
    search.name.map { someName =>
        components.addFilter(condEq("name", str(someName)))
    }.getOrElse(components)
  }

  /**
   * Returns a new QueryComponents object which contains the components pertaining to day filtering
   *
   * @param startDate Start date for filtering
   * @param endDate End date for filtering
   * @param components Components of the query being composed
   * @return object which contains the components pertaining to day filtering
   */
  private def applyDates(search: EventSearch, components: QueryComponents) : QueryComponents = {
    if(!search.searchDatesAreDefined){
      return components
    }
    val eventDates = TimeSeriesOperations.getEventDates(List(new DMDuration(search.startDate.get, search.endDate.get)))
    val daysQuery = TimeSeriesQueries.getDaysQuery(eventDates.getDays())
    val expandedEvents = expandEventDateFromDays(daysQuery)
    components.setTarget(expandedEvents)
  }

  /**
   * Composes a query (over the query to fetch the days) which returns the events related to a collection of days
   *
   * @param daysQuery the query which retrieves a collection of days
   * @return The composed query
   */
  private def expandEventDateFromDays(daysQuery: SQLStatement) : SQLStatement  = {
    select(
      function(Functions.expand,
        function(Functions.in, "EventDate"))
    ).from(daysQuery)
  }
}