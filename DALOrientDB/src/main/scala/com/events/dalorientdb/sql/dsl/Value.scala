package com.events.dalorientdb.sql.dsl

import com.events.dalorientdb.sql.ValueStatement

/**
 * Created by pedro on 05-04-2015.
 */
object value {
  def apply(value: String) : ValueStatement[String] = {
    new ValueStatement[String](value)
  }
}
