package com.events.util.date

import org.joda.time._
import org.joda.time.format._

/**
 * @author pedro
 */
object DateHelper {
  val defaultDateSeparator = "/"
  val defaultTimeSeparator = ":"
  val yearFormat = "yyyy"
  val monthFormat = "MM"
  val dayFormat = "dd"
  val hourFormat = "HH"
  val minuteFormat = "mm"
  
  def loopDays(startDate: DateTime, endDate: DateTime)(body: DateTime => Unit) {
    var dateIter = startDate
    while (dateIter.compareTo(endDate) <= 0) {
      body(dateIter)
      dateIter = dateIter.plusDays(1)
    }
  }
  
  def toYMD(date: DateTime) : String = {
    val y = yearFormat
    val m = monthFormat
    val d = dayFormat
    val s = defaultDateSeparator
    val formatter = DateTimeFormat.forPattern(s"$y$s$m$s$d")
    formatter.print(date)
  }
  
  def toYMDHS(date: DateTime) : String = {
    val y = yearFormat
    val M = monthFormat
    val d = dayFormat
    val ds = defaultDateSeparator
    val ts = defaultTimeSeparator
    val h = hourFormat
    val m = minuteFormat
    val formatter = DateTimeFormat.forPattern(s"$y$ds$M$ds$d $h$ts$m")
    formatter.print(date)
  }
}