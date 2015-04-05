package com.events.domainmodel.event

import com.events.domainmodel.search.Paging
import com.github.nscala_time.time.Imports._

/**
 * @author pedro
 */
object EventSearch
{
  def apply(name: String,
            userLocation: Location,
            radius: Radius,
            startDate: DateTime,
            endDate: DateTime,
            categories: Traversable[Category],
            tags: Traversable[Tag])
           (paging: Option[Paging]): EventSearch = {
    new EventSearch(Some(name), Some(userLocation), Some(radius), Some(startDate), Some(endDate), categories, tags)(paging)
  }

  def apply(name: String): EventSearch = {
    new EventSearch(Some(name), None, None, None, None, None, None)(None)
  }

  def apply(startDate: DateTime, endDate: DateTime): EventSearch = {
    new EventSearch(None, None, None, Some(startDate), Some(endDate), None, None)(None)
  }
}

class EventSearch private(
    val name: Option[String],
    val userLocation: Option[Location],
    val radius: Option[Radius],
    val startDate: Option[DateTime],
    val endDate: Option[DateTime],
    val categories: Traversable[Category],
    val tags: Traversable[Tag])
    (val paging: Option[Paging])
{
  def searchDatesAreDefined() : Boolean = {
    startDate.isDefined && endDate.isDefined
  }
}