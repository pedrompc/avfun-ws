package com.events.dalorientdb.timeseries

import com.events.dalorientdb.{ Classes, Functions }
import com.github.nscala_time.time.Imports._
import org.joda.time.Days
import com.events.dalorientdb.sql._
import com.events.dalorientdb.sql.dsl._

/**
 * @author pedro
 */
object TimeSeriesQueries {
  val dayResultAlias = "dayResult"
  val dayProjectionTemplate = "year[%s].month[%s].day[%s].@rid"
  
  def getDaysQuery(dates: Traversable[DateTime]): String = {
    val dayProjections = getDayProjections(dates)
    select(
        function(Functions.expand, 
            function(Functions.unionall, dayProjections))
    ).from(Classes.timeSeries).eval()
  }
  
  def getDayProjection(dateTime: DateTime) : String = {
    dayProjectionTemplate.format(dateTime.getYear, dateTime.getMonthOfYear, dateTime.getDayOfMonth) 
  }
  
  private def getDayProjections(dates: Traversable[DateTime]) : Traversable[String] = {
    var dayProjections = List[String]()
    dates.foreach {
      date =>
      val dayProjection = getDayProjection(date)
      dayProjections = dayProjections :+ dayProjection
    }
    
    dayProjections
  }
}