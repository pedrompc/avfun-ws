package com.events.dalorientdb.event

import org.joda.time.DateTime
import com.tinkerpop.blueprints.impls.orient._

/**
 * @author pedro
 */
class EventDateEdge(
    val eventVtx: OrientVertex,
    override val dayVtx: OrientVertex,
    override val startTime: DateTime,
    override val endTime: DateTime) extends EventDayTime(dayVtx, startTime, endTime)
{
}