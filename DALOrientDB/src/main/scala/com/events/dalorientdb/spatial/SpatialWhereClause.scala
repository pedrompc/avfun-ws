package com.events.dalorientdb.spatial

import com.events.dalorientdb.query.WhereClause

/**
 * @author pedro
 */
trait SpatialWhereClause extends WhereClause {
  override def getClauses : String = {
    ""
  }
}