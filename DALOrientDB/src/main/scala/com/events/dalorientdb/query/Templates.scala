package com.events.dalorientdb.query

/**
 * @author pedro
 */
object Templates {
  def getEqualsFilter(leftSide: String, rightSide: String) = s"$leftSide = $rightSide"
}