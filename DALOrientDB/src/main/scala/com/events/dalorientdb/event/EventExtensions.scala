package com.events.dalorientdb.event

import com.events.domainmodel.event._
import com.tinkerpop.blueprints.impls.orient._

/**
 * @author pedro
 */
object EventExtensions {
  class VertexMappableEvent(
      val event: Event)
  {
    def mapTo(vertex: OrientVertex) = {
      mapName(vertex)
      mapLocation(vertex)
    }
    
    def mapName(vertex: OrientVertex) = {
      if(this.event.name != null){
        vertex.setProperty("name", this.event.name)
      }
    }
    
    def mapLocation(vertex: OrientVertex) = {
      if(this.event.location != null){
        vertex.setProperty("latitude", this.event.location.latitude)
        vertex.setProperty("longitude", this.event.location.longitude)
      }
    }
  }
  
  implicit def mapEventToVertex(event: Event) = new VertexMappableEvent(event)
}