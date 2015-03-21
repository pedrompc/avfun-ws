package com.events.dalorientdb.query

/**
 * @author pedro
 */
class ExpandQuery(subQueries: Traversable[Query]) extends Query{
  class ExpandSubQueriesResult(
      val queryVar: QueryVar,
      val expandSubQueries: Traversable[ExpandSubQuery])
  {}
  
  val expandQueryTemplate = "select expand(%s) let %s, %s = unionall(%s)"
  
  override def getQuery() : String = {
    val expandSubQueriesResult = getExpandSubQueries(subQueries)
    val expandVariable = getExpandVariable(expandSubQueriesResult)
    val subQueriesList = getSubQueriesList(expandSubQueriesResult)
    val unionAllList = getUnionAllList(expandSubQueriesResult)
    
    expandQueryTemplate.format(expandVariable, subQueriesList, expandVariable, unionAllList)
  }
  
  private def getExpandVariable(subQueriesResult: ExpandSubQueriesResult) : String = {
    subQueriesResult.queryVar.nextQueryVar().getVariable()
  }
  
  private def getSubQueriesList(subQueriesResult: ExpandSubQueriesResult) : String = {
    subQueriesResult.expandSubQueries.map {
      q => 
        q.getQuery()
    }.mkString(",")
  }
  
  private def getUnionAllList(subQueriesResult: ExpandSubQueriesResult) : String = {
    subQueriesResult.expandSubQueries.map {
      q => 
        q.variableName
    }.mkString(",")
  }
  
  private def getExpandSubQueries(subQueries: Traversable[Query]) : ExpandSubQueriesResult = {
    var expandSubQueries = List[ExpandSubQuery]()
    var queryVar = new QueryVar("p", 1)
    
    for(subQuery <- subQueries){
      val expandSubQuery = new ExpandSubQuery(subQuery, queryVar.getVariable())
      expandSubQueries = expandSubQuery :: expandSubQueries
      queryVar = queryVar.nextQueryVar()
    }
    
    new ExpandSubQueriesResult(queryVar, expandSubQueries) 
  }
}