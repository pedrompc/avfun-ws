package com.events.domainmodel.event

import com.events.domainmodel.search.Paging
import com.github.nscala_time.time.Imports._

/**
 * @author pedro
 */
class EventSearch(
    val name: Option[String],
    val userLocation: Location, 
    val radius: Radius, 
    val startDate: DateTime,
    val endDate: DateTime,
    val categories: Traversable[Category],
    val tags: Traversable[Tag])
    (val paging: Option[Paging])
{ 
  def this(userLocation: Location, radius: Radius) = this(None, userLocation, radius, null, null, List(), List())(None)
}