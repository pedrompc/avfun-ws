package com.events.dalorientdb

import com.events.dal._
import com.events.dalorientdb.event.EventRepositoryOrientDB
import com.tinkerpop.blueprints.impls.orient._
import javax.inject.{Inject}


/**
 * @author pedro
 */
class UnitOfWorkProviderOrientDB @Inject() (config: UnitOfWorkConfiguration) extends UnitOfWorkProvider{
  lazy val graphFactory : OrientGraphFactory = new OrientGraphFactory(config.connectionString)
  
  def getUnitOfWork : UnitOfWork = {
    return new UnitOfWorkOrientDB(graphFactory)
  }
}