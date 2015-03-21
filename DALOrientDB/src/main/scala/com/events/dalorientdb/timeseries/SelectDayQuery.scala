package com.events.dalorientdb.timeseries

import com.github.nscala_time.time.Imports._
import com.events.dalorientdb.query._

/**
 * @author pedro
 */
class SelectDayQuery(
    val resultAlias: String,
    val datetime: DateTime) extends SelectQuery[DateTime]
{
  val selectQueryTemplate : String = "select month[%s].day[%s] as %s from Year where year = %s"
  
  override def getQuery() : String = {
    selectQueryTemplate.format(datetime.month.get(), datetime.day.get(), resultAlias, datetime.year.get())
  }
}