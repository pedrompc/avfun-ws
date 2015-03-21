package com.events.dalorientdb.event

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import com.events.dalorientdb.query._
import com.events.domainmodel.event._
import com.tinkerpop.blueprints.impls.orient._
import com.events.dalorientdb.query.CreateEdgeQuery
import org.joda.time.DateTime
import com.events.util.date.DateHelper

/**
 * @author pedro
 */
class EventDateEdgeMapper(
      val graph: OrientGraph) 
{
  val edgeClassName = "EventDate"
  
  def create(edge: EventDateEdge) : Unit = {
    val newEdge = graph.addEdge(null, edge.dayVtx, edge.eventVtx, edgeClassName)
    setTimes(newEdge, edge.startTime, edge.endTime)
  }
  
  def setTimes(edge: OrientEdge, startTime: DateTime, endTime: DateTime) {
    if(startTime != null){
      edge.setProperty("startTime", DateHelper.toYMDHS(startTime))
    }
    
    if(endTime != null){
      edge.setProperty("endTime", DateHelper.toYMDHS(endTime))
    }
  }
}