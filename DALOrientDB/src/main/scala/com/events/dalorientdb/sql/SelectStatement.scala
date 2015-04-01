package com.events.dalorientdb.sql

/**
 * @author pedro
 */
class SelectStatement(
    val projections: Traversable[SQLStatement]) extends SQLStatement
{
  val projectionSeparator = ","
  val statementTemplate = "select %s"
  
  def this() = this(List())
  
  def eval() : String = {
    val evaledProjections = projections.map { _.eval() }
    statementTemplate.format(evaledProjections.mkString(projectionSeparator).trim())
  }
  
  def select(moreProjections: Traversable[SQLStatement]) = new SelectStatement(List.concat(projections, moreProjections))
  
  def from(fromTarget: String) : FromStatement = new FromStatement(this, new ValueStatement[String](fromTarget))
  
  def from(fromTarget: SQLStatement) : FromStatement = new FromStatement(this, fromTarget)
}