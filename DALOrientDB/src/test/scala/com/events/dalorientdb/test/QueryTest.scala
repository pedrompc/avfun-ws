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
  
  "getDaysQuery" should "return a valid query for one day" in {
    val date = new DateTime(2015, 3, 21, 0, 0)
    assertResult("select expand(unionall(year[2015].month[3].day[21].@rid)) from TimeSeries") {
      TimeSeriesQueries.getDaysQuery(List(date))
    }
  }
  
  "getDaysQuery" should "return a valid query for two days" in {
    val date1 = new DateTime(2015, 3, 21, 0, 0)
    val date2 = new DateTime(2015, 3, 22, 0, 0)
    assertResult("select expand(unionall(year[2015].month[3].day[21].@rid,year[2015].month[3].day[22].@rid)) from TimeSeries") {
      TimeSeriesQueries.getDaysQuery(List(date1, date2))
    }
  }
}