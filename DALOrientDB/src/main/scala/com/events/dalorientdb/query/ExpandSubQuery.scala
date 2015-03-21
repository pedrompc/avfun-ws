package com.events.dalorientdb.query{
  /**
   * @author pedro
   */
  class ExpandSubQuery(
      query: Query, 
      val variableName: String) extends Query
{
    val expandSubQueryTemplate = "%s = (%s)"
    
    def getQuery() : String = {
      expandSubQueryTemplate.format(variableName, query.getQuery())
    }
  }
}

