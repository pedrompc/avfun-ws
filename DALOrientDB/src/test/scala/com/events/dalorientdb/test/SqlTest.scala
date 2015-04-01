package com.events.dalorientdb.test

import org.scalatest._
import com.events.dalorientdb.sql._

/**
 * @author pedro
 */
class SqlTest extends FlatSpec {
  "SelectStatement with projections" should "return a valid sql statement" in {
    val projections = List(new ValueStatement[String]("p1"), new ValueStatement[String]("p2"))
    assertResult("select p1,p2") { new SelectStatement(projections).eval() }
  }
  
  "SelectStatement with projections and from clause" should "return a valid sql statement" in {
    val projections = List(new ValueStatement[String]("p1"), new ValueStatement[String]("p2"))
    val selectStatement = new SelectStatement(projections)
    val fromStatement = selectStatement.from(new ValueStatement[String]("E"))
    assertResult("select p1,p2 from E") { fromStatement.eval() }
  }
  
  "SelectStatement with projections, from clause and where clause" should "return a valid sql statement" in {
    val projections = List(new ValueStatement[String]("p1"), new ValueStatement[String]("p2"))
    val selectStatement = new SelectStatement(projections)
    val fromStatement = selectStatement.from(new ValueStatement[String]("E"))
    val conditionStatement = new UnaryConditionStatement(new ValueStatement[String]("p1 = 1"))
    val whereStatement = fromStatement.where(conditionStatement)
    assertResult("select p1,p2 from E where p1 = 1") { whereStatement.eval() }
  }
  
  "condition composition in where clause" should "return a valid sql statement" in {
    val projections = List(new ValueStatement[String]("p1"), new ValueStatement[String]("p2"))
    val selectStatement = new SelectStatement(projections)
    val fromStatement = selectStatement.from(new ValueStatement[String]("E"))
    val condition1 = new UnaryConditionStatement(new ValueStatement[String]("p1 = 1"))
    val condition2 = new UnaryConditionStatement(new ValueStatement[String]("p2 = 1"))
    val whereStatement = fromStatement.where(condition1.and(condition2))
    assertResult("select p1,p2 from E where p1 = 1 and p2 = 1") { whereStatement.eval() }
  }
  
  "where clause composition" should "merge the conditions and return a valid sql statement" in {
    val projections = List(new ValueStatement[String]("p1"), new ValueStatement[String]("p2"))
    val selectStatement = new SelectStatement(projections)
    val fromStatement = selectStatement.from(new ValueStatement[String]("E"))
    val condition1 = new UnaryConditionStatement(new ValueStatement[String]("p1 = 1"))
    val condition2 = new UnaryConditionStatement(new ValueStatement[String]("p2 = 1"))
    val whereStatement = fromStatement.where(condition1).where(condition2)
    assertResult("select p1,p2 from E where p1 = 1 and p2 = 1") { whereStatement.eval() }
  }
}