import org.scalatest._
import com.events.dalorientdb.event._
import org.joda.time._
import com.events.dalorientdb.timeseries.{Day}

class EventDatesTest extends FlatSpec {
  def getEventDates = new EventDates
  
  "getDays" should "return empty list if no days have been added" in {
    val eventDates = getEventDates
    var days = eventDates.getDays()
    assert(days.isEmpty)
  }
  
  "getDayTimes(DateTime)" should "return empty list if no days have been added" in {
    val eventDates = getEventDates
    var days = eventDates.getDayTimes(DateTime.now)
    assert(days.isEmpty)
  }
  
  "getDayTimes(Day)" should "return empty list if no days have been added" in {
    val eventDates = getEventDates
    val day = DateTime.now.getDayOfMonth
    val month = DateTime.now.getMonthOfYear
    val year = DateTime.now.getYear
    val date = new Day(year, month, day)
    var days = eventDates.getDayTimes(date)
    assert(days.isEmpty)
  }
  
  "getDays" should "should return the same day that is added" in {
    val eventDates = getEventDates
    val date = DateTime.now
    val startTime = DateTime.now
    val endTime = DateTime.now
    eventDates.addDate(date, startTime, endTime)
    val days = eventDates.getDays
    assert(days.size == 1)
    val addedDay = days.head
    assert(addedDay.getYear == date.getYear)
    assert(addedDay.getMonthOfYear == date.getMonthOfYear)
    assert(addedDay.dayOfMonth == date.dayOfMonth)
  }
  
  "getDayTimes" should "should return the same startTime and endTime that were added for a day" in {
    val eventDates = getEventDates
    val date = DateTime.now
    val startTime = DateTime.now
    val endTime = DateTime.now
    eventDates.addDate(date, startTime, endTime)
    val dayTimesOpt = eventDates.getDayTimes(date)
    val dayTimes = dayTimesOpt.get
    
    assert(dayTimesOpt.isDefined)
    assert(dayTimes.size == 1)
    
    val addedDayTimes = dayTimes.head
    assert(addedDayTimes._1.getHourOfDay == startTime.getHourOfDay)
    assert(addedDayTimes._1.getMinuteOfHour == startTime.getMinuteOfHour)
    assert(addedDayTimes._1.getSecondOfMinute == startTime.getSecondOfMinute)
    
    assert(addedDayTimes._2.getHourOfDay == endTime.getHourOfDay)
    assert(addedDayTimes._2.getMinuteOfHour == endTime.getMinuteOfHour)
    assert(addedDayTimes._2.getSecondOfMinute == endTime.getSecondOfMinute)
  }
  
  "getDays" should "should return only one day if two equal days are added" in {
    val eventDates = getEventDates
    val date = DateTime.now
    val startTime = DateTime.now
    val endTime = DateTime.now
    eventDates.addDate(date, startTime, endTime)
    eventDates.addDate(date, startTime, endTime)
    val days = eventDates.getDays
    assert(days.size == 1)
    val addedDay = days.head
    assert(addedDay.getYear == date.getYear)
    assert(addedDay.getMonthOfYear == date.getMonthOfYear)
    assert(addedDay.dayOfMonth == date.dayOfMonth)
  }
  
  "getDayTimes" should "should return one set of day times for same day if two equal dates are added" in {
    val eventDates = getEventDates
    val date = DateTime.now
    val startTime = DateTime.now
    val endTime = DateTime.now
    eventDates.addDate(date, startTime, endTime)
    eventDates.addDate(date, startTime, endTime)
    val dayTimesOpt = eventDates.getDayTimes(date)
    val dayTimes = dayTimesOpt.get
    
    assert(dayTimesOpt.isDefined)
    assert(dayTimes.size == 1)
  }
  
  
  "getDayTimes" should "should return two sets of day times for same day if two different ones are added" in {
    val eventDates = getEventDates
    val date = DateTime.now
    val startTime = DateTime.now
    val endTime = DateTime.now
    eventDates.addDate(date, startTime, endTime)
    eventDates.addDate(date, startTime.plusDays(1), endTime.plusDays(1))
    val dayTimesOpt = eventDates.getDayTimes(date)
    val dayTimes = dayTimesOpt.get
    
    assert(dayTimesOpt.isDefined)
    assert(dayTimes.size == 2)
  }
}
