import org.scalatest._
import com.events.util.date._
import org.joda.time._

/**
 * @author pedro
 */
class DateHelperTest extends FlatSpec{
  "loopDays" should "loop correctly between two days" in {
    val initialDate = DateTime.now
    val daysApart = 3
    var currIteration = 0;
    DateHelper.loopDays(initialDate, initialDate.plusDays(daysApart)){
      currDay =>
        val dayApart = initialDate.plusDays(currIteration)
        assert(currDay.getYear == dayApart.getYear)
        assert(currDay.getMonthOfYear == dayApart.getMonthOfYear)
        assert(currDay.getDayOfMonth == dayApart.getDayOfMonth)
        currIteration += 1
    }
    
    // +1, counting the initial day
    assert(currIteration == daysApart + 1)
  }
  
  "toYMD" should "return a string in yyyy/MM/dd format" in {
    val date = new DateTime(2015, 3, 21, 0, 0)
    assertResult("2015/03/21") { DateHelper.toYMD(date) }
  }
  
  "toYMDHS" should "return a string in yyyy/MM/dd HH:mm format" in {
    val date = new DateTime(2015, 3, 21, 17, 20)
    assertResult("2015/03/21 17:20") { DateHelper.toYMDHS(date) }
  }
}