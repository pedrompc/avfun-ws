package com.events.dalorientdb.event

import scala.concurrent.Promise
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import com.events.domainmodel.event._
import com.events.dal.event._
import com.tinkerpop.blueprints.impls.orient._
import EventExtensions._
import java.util.HashMap
import com.orientechnologies.orient.core.sql.query._
import com.tinkerpop.blueprints.impls.orient._
import com.events.dalorientdb.timeseries._
import org.joda.time.DateTime
import com.events.util.promiseexec._
import com.events.dalorientdb.MapperOptions

class EventRepositoryOrientDB (graph: OrientGraph) extends EventRepository {
  override def create(event: Event) : Future[String] = { 
    PromiseExec[String] {
      promise => 
      val tsOps = new TimeSeriesOperations(graph)
      val eventMapper = new EventVtxMapper(graph, new MapperOptions(true))
      val edgeMapper = new EventDateEdgeMapper(graph)
      val getDaysFut = tsOps.getDayVertexesFromDurations(event.durations)
      val createEvFut = eventMapper.create(event)
      
      val combined = for{
        getDaysFutResult <- getDaysFut      
        createEvFutResult <- createEvFut
      } yield (getDaysFutResult, createEvFutResult)
      
      combined.onSuccess {
        case combinedResult => 
          PromiseTry(promise){
            combinedResult._1.foreach {
              day => 
              edgeMapper.create(new EventDateEdge(day.dayVtx, combinedResult._2, day.startTime, day.endTime))              
            }
          }
          
          promise.success(combinedResult._2.getId().toString())
      }
      
      combined.onFailure {
        case cause : Exception => promise.failure(cause)
      }  
    }
  }
  
  override def search(searchTerms: EventSearch) : Future[Traversable[Event]] = {
    Future {
      //val query = convert st to query
      //new VertexQueryExec(graph, null)
      null
    }
  }
}
