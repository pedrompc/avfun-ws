package com.events.domainmodel.event

/**
 * @author pedro
 */
class Radius (
  distance: Double,
  unit: DistanceUnit)
{ 
  def this(distance: Double) = this(distance, Km)
}