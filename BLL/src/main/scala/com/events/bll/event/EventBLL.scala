package com.events.bll.event

import scala.concurrent.Future
import com.events.domainmodel.event._

abstract class EventBLL {
  def createEvent(event: Event) : Future[Unit]
  def search(searchTerms: EventSearch) : Future[Traversable[Event]]
}
