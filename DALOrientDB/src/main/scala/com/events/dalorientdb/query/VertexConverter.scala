package com.events.dalorientdb.query

import com.tinkerpop.blueprints.impls.orient._

/**
 * @author pedro
 */
class VertexConverter(
    val propertyName: String) extends ResultConverter[OrientVertex, OrientVertex]
{
  def convert(result: OrientVertex) : OrientVertex = {
    result.getProperty[OrientVertex](propertyName)
  }
}