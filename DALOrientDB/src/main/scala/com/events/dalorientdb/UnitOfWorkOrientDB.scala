package com.events.dalorientdb

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import com.events.dal._
import com.events.dal.event._
import com.events.dalorientdb.event.EventRepositoryOrientDB
import com.tinkerpop.blueprints.impls.orient._
import javax.inject.{Inject}
import com.orientechnologies.orient.core.exception.OConcurrentModificationException

/**
 * @author pedro
 */
class UnitOfWorkOrientDB (graphFactory: OrientGraphFactory) extends UnitOfWork{
  // all repositories in this instance will use the same graph 
  lazy val graph = graphFactory.getTx()
  lazy val eventRepository = new EventRepositoryOrientDB(graph)
  
  override def getEventRepository() : EventRepositoryOrientDB = {
    return eventRepository;
  }
  
  override def begin() {
    graph.begin()
  }
  
  override def commit() {
    graph.commit();
  }
  
  override def close() = {
    graph.rollback()
    graph.shutdown()
  }
  
  override def isRetriableException(exception: Exception) : Boolean = {
    exception match {
      case _ : OConcurrentModificationException => true
      case default => false
    }
  }
}