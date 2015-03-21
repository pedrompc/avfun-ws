package com.events.dal

import scala.concurrent.Future
import com.events.dal.event._

/**
 * @author pedro
 */
abstract class UnitOfWork {
  def getEventRepository() : EventRepository
  def begin() : Unit
  def commit() : Unit
  def close() : Unit
  def isRetriableException(exception: Exception) : Boolean
}