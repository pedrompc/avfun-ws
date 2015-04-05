package com.events.dalorientdb.test

import com.events.dalorientdb.event.EventSearchQuery
import com.events.domainmodel.event.EventSearch
import org.joda.time.DateTime
import org.scalatest.FlatSpec

/**
 * Created by pedro on 05-04-2015.
 */
class EventSearchTest extends FlatSpec{
  def getSearchTermsWithName : EventSearch = {
    EventSearch("event")
  }

  def getSearchTermsWithDates : EventSearch = {
    val startDate = new DateTime(2015, 3, 21, 0, 0)
    val endDate = new DateTime(2015, 3, 22, 0, 0)
    EventSearch(startDate, endDate)
  }

  "EventSearchQuery.getQuery" should "return a valid sql query for a name search" in {
    val searchTerms = getSearchTermsWithName
    val searchName = searchTerms.name.get
    val evSearchQuery = new EventSearchQuery(searchTerms)
    assertResult(s"select from Event where name = '$searchName'"){
      evSearchQuery.getQuery().eval()
    }
  }

  "EventSearchQuery.getQuery" should "return a valid sql query for a date search" in {
    val searchTerms = getSearchTermsWithDates
    val startDate = searchTerms.startDate.get
    val endDate = searchTerms.endDate.get
    val startYear = startDate.getYear
    val startMonth = startDate.getMonthOfYear
    val startDay = startDate.getDayOfMonth
    val endYear = endDate.getYear
    val endMonth = endDate.getMonthOfYear
    val endDay = endDate.getDayOfMonth
    val evSearchQuery = new EventSearchQuery(searchTerms)
    assertResult(s"select from (select expand(in('EventDate')) from (select expand(unionall(year[$startYear].month[$startMonth].day[$startDay].@rid,year[$endYear].month[$endMonth].day[$endDay].@rid)) from TimeSeries))"){
      evSearchQuery.getQuery().eval()
    }
  }
}
