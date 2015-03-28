package com.events.bllimpl.event

import scala.concurrent.Future
import scala.concurrent.Promise
import scala.concurrent.ExecutionContext.Implicits.global
import com.events.util.batchexec._
import com.events.dal._
import com.events.domainmodel.event._
import com.events.bll.event._
import javax.inject.{Inject}
import resource._

class EventBLLImpl @Inject() (
    unitOfWorkProvider : UnitOfWorkProvider,
    batchExec: AsyncBatchExec) extends EventBLL 
{
  override def createEvent(event: Event) : Future[Unit] = {
    batchExec.exec[Unit] { context =>
      val unitOfWork = unitOfWorkProvider.getUnitOfWork()
      context.enlistCloseable(unitOfWork)
      unitOfWork.begin()
      val createEventFut = unitOfWork.getEventRepository().create(event)
      createEventFut.map { _ =>
        unitOfWork.commit()
        context.success()
      }
      .onFailure {
        case cause : Exception => { 
          if(unitOfWork.isRetriableException(cause))
          {
            context.failure(new RetryException(cause))
          }
          else {
            context.failure(cause)
          }
        }
      }
    }
  }
  
  override def search(searchTerms: EventSearchTerms) : Future[Traversable[Event]] = {
    val unitOfWork = unitOfWorkProvider.getUnitOfWork()
    unitOfWork.getEventRepository().search(searchTerms)
  }
}
