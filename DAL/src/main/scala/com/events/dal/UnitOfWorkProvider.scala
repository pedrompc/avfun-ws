package com.events.dal

/**
 * @author pedro
 */
abstract class UnitOfWorkProvider {
  def getUnitOfWork() : UnitOfWork
}