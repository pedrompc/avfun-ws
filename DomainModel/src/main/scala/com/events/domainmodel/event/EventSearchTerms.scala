package com.events.domainmodel.event

import com.github.nscala_time.time.Imports._

/**
 * @author pedro
 */
class EventSearchTerms(
    userLocation: Location, 
    radius: Radius, 
    startDate: DateTime,
    endDate: DateTime,
    categories: Traversable[Category],
    tags: Traversable[Tag])
{
  def this(userLocation: Location, radius: Radius) = this(userLocation, radius, null, null, List(), List())
}