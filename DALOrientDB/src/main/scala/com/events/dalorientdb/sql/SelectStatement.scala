package com.events.dalorientdb.sql

/**
 * @author pedro
 */
class SelectStatement(
    protected val projections: Traversable[SQLStatement]) extends SQLStatement
{
  protected val projectionSeparator = ","
  protected val statementTemplate = "select %s"
  
  def this() = this(List())
  
  def eval() : String = {
    val evaledProjections = projections.map { _.eval() }
    statementTemplate.format(evaledProjections.mkString(projectionSeparator).trim())
  }
  
  def select(moreProjections: Traversable[SQLStatement]) = new SelectStatement(List.concat(projections, moreProjections))
  
  def from(fromTarget: SQLStatement) : FromStatement = new FromStatement(this, fromTarget)
}