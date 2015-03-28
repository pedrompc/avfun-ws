package controllers

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import javax.inject.{Inject}
import play.api._
import play.api.mvc._
import com.events.bll.event._
import com.events.domainmodel.event._
import org.joda.time.DateTime

class EventController @Inject() (eventBLL: EventBLL) extends Controller {
  def index = Action.async {
    val duration1 = new Duration(DateTime.now, DateTime.now.plusDays(2), DateTime.now, DateTime.now)
    val duration2 = new Duration(DateTime.now, DateTime.now.plusDays(2), DateTime.now, DateTime.now)
    val duration3 = new Duration(DateTime.now.minusDays(1), DateTime.now.minusDays(1), DateTime.now, DateTime.now)
    val durations = List(duration1, duration2, duration3)
    val fut = eventBLL.createEvent(new Event("pedro", new Location(38.8594712, -9.0626379), null, null, null, durations))
    fut onFailure {
      case t => Ok(views.html.index("fail"))
    }
    
    fut.map{_ => 
      Ok(views.html.index("ok"))
    }
  }
}
