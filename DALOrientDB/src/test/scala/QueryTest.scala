import org.scalatest._
import com.events.dalorientdb.query._
import org.joda.time._
import com.events.dalorientdb.timeseries._

/**
 * @author pedro
 */
class QueryTest extends FlatSpec {
  "CreateEdgeQuery" should "return a valid sql query" in {
    val createEdgeQuery = new CreateEdgeQuery("TestClass", "#1.1", "#1.2")
    assertResult("create edge TestClass from #1.1 to #1.2") { createEdgeQuery.getQuery }
  }
  
  "SelectDayQuery" should "return a valid sql query" in {
    val date = new DateTime(2015, 3, 21, 0, 0)
    val createEdgeQuery = new SelectDayQuery("Alias", date)
    assertResult("select month[3].day[21] as Alias from Year where year = 2015") { createEdgeQuery.getQuery }
  }
}