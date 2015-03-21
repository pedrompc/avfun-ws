package com.events.dalorientdb.event

import com.events.dalorientdb.timeseries.{TimeSeriesOperations, Day}
import scala.collection.mutable.{HashMap, MultiMap, Set}
import org.joda.time._
import java.util.Formatter.DateTime

/**
 * @author pedro
 */
class EventDates {
  private val dates = new HashMap[Day, Set[Tuple2[DateTime, DateTime]]] with MultiMap[Day, Tuple2[DateTime, DateTime]]
  
  def addDate(date: DateTime, startTime: DateTime, endTime: DateTime) {
    val day = TimeSeriesOperations.getDay(date)
    dates.addBinding(day, (startTime, endTime))
  }
  
  def getDays() : Traversable[DateTime] = {
    dates.keySet.map { day =>
      new DateTime().withYear(day.year).withMonthOfYear(day.month).withDayOfMonth(day.day)
    }
  }
  
  def getDayTimes(date: DateTime) : Option[Traversable[Tuple2[DateTime, DateTime]]] = {
    val day = TimeSeriesOperations.getDay(date)
    getDayTimes(day)
  }
  
  def getDayTimes(day: Day) : Option[Traversable[Tuple2[DateTime, DateTime]]] = {
    dates.get(day)
  }
}