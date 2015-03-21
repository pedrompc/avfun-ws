package com.events.dalorientdb.event

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import com.events.domainmodel.event._
import java.util.HashMap
import EventExtensions._
import com.tinkerpop.blueprints.impls.orient._
import com.events.dalorientdb._

class EventVtxMapper(
    val graph: OrientGraph,
    val options: MapperOptions) 
{
  def this(graph: OrientGraph){
    this(graph, null)
  }
  
  def create(event: Event) : Future[OrientVertex] = {
    Future {
      if(event == null){
        throw new IllegalArgumentException("entity to create cannot be null")
      }    
      val newVertex : OrientVertex = graph.addVertex(null, null)
      var properties = new HashMap[String, String]()
      properties.put("@class", "Event")
      newVertex.setProperties(properties)
      event.mapTo(newVertex)
      newVertex
    }
  }
  
  def shouldSave() : Boolean = {
    options != null && options.commitOnWrite
  }
  
  def save() {
    if(shouldSave){
      graph.commit()
    }
  }
}