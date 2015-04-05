package com.events.dalorientdb.sql.dsl

import com.events.dalorientdb.sql.{StringStatement, SQLStatement}

/**
 * Created by pedro on 05-04-2015.
 */
object str {
  def apply(value: String) : StringStatement = {
    new StringStatement(value)
  }
}
