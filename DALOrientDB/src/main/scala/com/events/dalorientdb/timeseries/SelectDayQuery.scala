package com.events.dalorientdb.timeseries

import com.events.dalorientdb.Classes
import com.github.nscala_time.time.Imports._
import com.events.dalorientdb.query._

/**
 * @author pedro
 */
class SelectDayQuery(
    val resultAlias: String,
    val datetime: DateTime) extends SelectQuery with WhereClause
{
  val dayTemplate = "month[%s].day[%s] as %s"
  val clauseTemplate = "year = %s"
  
  override def getClauses() : String = {
    clauseTemplate.format(datetime.getYear)
  }
  
  override def getProjections() : String = {
    dayTemplate.format(datetime.getMonthOfYear, datetime.getDayOfMonth, resultAlias)
  }
  
  def getFrom() : String = {
    Classes.year
  }
}