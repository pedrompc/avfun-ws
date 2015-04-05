package com.events.dalorientdb.sql.dsl

import com.events.dalorientdb.sql._

/**
 * @author pedro
 */
object function {
  def apply(name: String, values: String*) : FunctionStatement = {
    new FunctionStatement(name, values.map { new StringStatement(_) })
  }
  
  def apply(name: String, value: SQLStatement) : FunctionStatement = {
    new FunctionStatement(name, List(value))
  }

  def apply(name: String, values: Traversable[SQLStatement]) : FunctionStatement = {
    new FunctionStatement(name, values)
  }
}