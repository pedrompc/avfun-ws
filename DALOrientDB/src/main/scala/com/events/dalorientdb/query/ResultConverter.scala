package com.events.dalorientdb.query

abstract class ResultConverter[O, T] {
  def convert(convertable: O) : T
}