package com.events.dalorientdb.event

import com.tinkerpop.blueprints.impls.orient._
import org.joda.time.DateTime

/**
 * @author pedro
 */
class EventDayTime (
    val dayVtx: OrientVertex,
    val startTime: DateTime,
    val endTime: DateTime) 
{
}