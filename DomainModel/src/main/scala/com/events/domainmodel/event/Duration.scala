package com.events.domainmodel.event

import com.github.nscala_time.time.Imports._

/**
 * @author pedro
 */
class Duration(
    val startDate: DateTime, 
    val endDate: DateTime,
    val startTime: DateTime,
    val endTime: DateTime) 
{
  def this(startDate: DateTime, endDate: DateTime){
    this(startDate, endDate,  null, null)
  }
  
  def this(startDate: DateTime, endDate: DateTime, startTime: DateTime){
    this(startDate, endDate,  startTime, null)
  }
}