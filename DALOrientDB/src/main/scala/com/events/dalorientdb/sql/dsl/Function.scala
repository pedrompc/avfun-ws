package com.events.dalorientdb.sql.dsl

import com.events.dalorientdb.sql._

/**
 * @author pedro
 */
object function {
  def apply(name: String, values: String*) : FunctionStatement = {
    new FunctionStatement(name, values.map { new ValueStatement[String](_) })
  }
  
  def apply(name: String, values: Traversable[String]) : FunctionStatement = {
    new FunctionStatement(name, values.map { new ValueStatement[String](_) })
  }
  
  def apply(name: String, value: SQLStatement) : FunctionStatement = {
    new FunctionStatement(name, List(value))
  }
}