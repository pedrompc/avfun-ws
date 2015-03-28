package com.events.dalorientdb.query

/**
 * @author pedro
 */
abstract class SelectQuery extends Query{
  val queryTemplate = "select %s from %s"
  
  def getProjections() : String
  
  def getFrom() : String
  
  def getQuery() : String = {
    queryTemplate.format(getProjections, getFrom)
  }
}