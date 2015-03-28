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
}