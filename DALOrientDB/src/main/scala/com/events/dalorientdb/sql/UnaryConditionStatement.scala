package com.events.dalorientdb.sql

/**
 * @author pedro
 */
class UnaryConditionStatement(
    protected val statement: ValueStatement[String]) extends ConditionStatement 
{
  override def eval() : String = {
    statement.eval()
  }  
}