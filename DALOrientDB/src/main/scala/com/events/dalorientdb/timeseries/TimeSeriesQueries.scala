package com.events.dalorientdb.timeseries

import com.github.nscala_time.time.Imports._
import org.joda.time.Days
import com.events.dalorientdb.query.ExpandQuery

/**
 * @author pedro
 */
object TimeSeriesQueries {
  val dayResultAlias = "dayResult"
  
  def getDaysQuery(dates: Traversable[DateTime]): String = {
    val dayQueries = getDaysSubQueries(dates)
    new ExpandQuery(dayQueries).getQuery()
  }
  
  private def getDaysSubQueries(dates: Traversable[DateTime]) : Traversable[SelectDayQuery] = {
    var dayQueries = List[SelectDayQuery]()
    dates.foreach {
      date =>
      val dayQuery = new SelectDayQuery(dayResultAlias, date)
      dayQueries = dayQueries :+ dayQuery
    }
    
    dayQueries
  }
  /*
  private def getDurationSubQueries(startDate: DateTime, endDate: DateTime) : Traversable[SelectDayQuery] = {
    var dayQueries = List[SelectDayQuery]()
    var dateIter = startDate
    while (dateIter.compareTo(endDate) <= 0) {
      val dayQuery = new SelectDayQuery(dayResultAlias, dateIter)
      dayQueries = dayQuery :: dayQueries
      dateIter = dateIter.plusDays(1)
    }
    dayQueries
  }*/
}