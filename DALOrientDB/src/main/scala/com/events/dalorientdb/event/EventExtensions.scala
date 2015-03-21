package com.events.dalorientdb.event

import com.events.domainmodel.event._
import com.tinkerpop.blueprints.impls.orient._

/**
 * @author pedro
 */
object EventExtensions {
  class VertexMappableEvent(val event: Event){
    def mapTo(vertex: OrientVertex) : Unit = {
      if(this.event.name != null){
        vertex.setProperty("name", this.event.name)
      }
    }
  }
  
  implicit def mapEventToVertex(event: Event) = new VertexMappableEvent(event)
}