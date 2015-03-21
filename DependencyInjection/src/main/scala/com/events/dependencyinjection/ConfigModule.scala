package com.events.dependencyinjection

import com.events.util.batchexec._
import com.events.util._
import com.google.inject.{Guice, AbstractModule}
import net.codingwell.scalaguice._
import com.events.dal._
import com.events.dal.event._
import com.events.dalorientdb._
import com.events.dalorientdb.event._
import com.events.bll.event._
import com.events.bllimpl.event._


class ConfigModule extends AbstractModule with ScalaModule{
  def configure {
    setupBLL()
    setupDAL()
  }
  
  def setupBLL() = {
    bind[EventBLL].to[EventBLLImpl]
  }
  
  def setupDAL() = {
    //val config = new UnitOfWorkConfiguration("plocal:/opt/orientdb-community-2.0.2/databases/events")
    val config = new UnitOfWorkConfiguration("remote:localhost:2424/events")
    val retries = 3;
    bind[UnitOfWorkConfiguration].toInstance(config)
    bind[AsyncBatchExec].toInstance(new AsyncBatchExecImpl(retries))
    //bind[OrientGraphFactory].. : OrientGraphFactory = new OrientGraphFactory("plocal:/opt/releases/orientdb-community-2.0.2/databases/events")
    //val graph : OrientGraph = factory.getTx()
    bind[UnitOfWorkProvider].to[UnitOfWorkProviderOrientDB]
  }
}
