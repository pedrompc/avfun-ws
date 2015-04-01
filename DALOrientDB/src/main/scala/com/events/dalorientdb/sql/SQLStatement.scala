package com.events.dalorientdb.sql

/**
 * @author pedro
 */
trait SQLStatement {
  def eval() : String
}