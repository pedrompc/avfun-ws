package com.events.dalorientdb.query

/**
 * @author pedro
 */
abstract class SelectQuery[T] extends Query{
  def getQuery() : String
}