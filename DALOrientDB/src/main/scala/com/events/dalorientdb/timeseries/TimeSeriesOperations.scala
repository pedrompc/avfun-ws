package com.events.dalorientdb.timeseries

import com.events.dalorientdb.event._
import com.events.domainmodel.event.Duration
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import com.tinkerpop.blueprints.impls.orient._
import com.github.nscala_time.time.Imports._
import com.events.dalorientdb.query._
import com.events.util.date._

object TimeSeriesOperations{
  def getDay(datetime: DateTime) : Day = {
    new Day(datetime.getYear(), datetime.getMonthOfYear(), datetime.getDayOfMonth())
  }
  
  def getDay(dayVtx: OrientVertex) : Day = {
    val day : Int = dayVtx.getProperty("day")
    val month : Int = dayVtx.getProperty("month")
    val year : Int = dayVtx.getProperty("year")
    new Day(year, month, day)
  }
}

class TimeSeriesOperations(graph: OrientGraph) {  
  
  def getDayVertexesFromDurations(durations: Traversable[Duration]) : Future[Traversable[EventDayTime]] = {   
    val eventDates = getEventDates(durations)
    val query = TimeSeriesQueries.getDaysQuery(eventDates.getDays())
    val queryExec = new VertexQueryExec[OrientVertex](graph)
    val dayVertexesFut = queryExec.exec(query, new VertexConverter(TimeSeriesQueries.dayResultAlias))
    dayVertexesFut.map { dayVertices => 
      getDayVerticesWithTimes(dayVertices, eventDates)
    }
  }
  
  def getDayVerticesWithTimes(dayVertices: Traversable[OrientVertex], eventDates: EventDates) : Traversable[EventDayTime] = {
    var eventDayTimes = List[EventDayTime]()
    dayVertices.foreach { dayVtx =>
      eventDayTimes = eventDayTimes ++ getDayTimesForVtx(dayVtx, eventDates)
    }
    eventDayTimes
  }
  
  def getDayTimesForVtx(dayVtx: OrientVertex, eventDates: EventDates) : Traversable[EventDayTime] = {
    val timeTuples = eventDates.getDayTimes(TimeSeriesOperations.getDay(dayVtx))
    timeTuples.map { someTimeTuples =>
      someTimeTuples.map { timeTuple =>
        new EventDayTime(dayVtx, timeTuple._1, timeTuple._2)
      }
    }
    .getOrElse(List())
  }
  
  def getEventDates(durations: Traversable[Duration]): EventDates = {
    val eventDates = new EventDates
    durations.foreach { duration =>
      DateHelper.loopDays(duration.startDate, duration.endDate){ 
        currDay =>
        eventDates.addDate(currDay, duration.startTime, duration.endTime)
      }
    }
    eventDates
  }
}