package com.events.dalorientdb.query

/**
 * @author pedro
 */
class CreateEdgeQuery(
    val className: String,
    val fromId: String,
    val toId: String)
{
  val createEdgeTemplate = "create edge %s from %s to %s"
  
  def getQuery() : String = {
    return createEdgeTemplate.format(className, fromId, toId)
  }
}