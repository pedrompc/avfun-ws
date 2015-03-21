package com.events.dalorientdb.query

/**
 * @author pedro
 */
class QueryVar(
    val prefix: String, 
    val number: Int) {
  
  val queryVarTemplate = "$%s%s"
  
  def getVariable() : String = {
    queryVarTemplate.format(prefix, number)
  }
  
  def nextQueryVar() : QueryVar = {
    new QueryVar(prefix, number + 1)
  }
}