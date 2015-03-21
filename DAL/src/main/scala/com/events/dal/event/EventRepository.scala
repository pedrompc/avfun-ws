package com.events.dal.event

import scala.concurrent.Future
import com.events.dal._
import com.events.domainmodel.event._

abstract class EventRepository{
  def create(entity: Event) : Future[String]
}
