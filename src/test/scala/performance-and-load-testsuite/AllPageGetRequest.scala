package gatling.performance.load.testsuite

import com.typesafe.config.ConfigFactory
import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._

class AllPageGetRequest extends Simulation {

  val conf = ConfigFactory.load()
  val baseUrl = "http://computer-database.gatling.io"
  //  val baseUrl = conf.getString("baseUrl")
  val httpProtocol = http.baseUrl(baseUrl) //sample comment
  //  Header Configuration
  val header_0 = Map("Accept" -> "text / html")

  object Developer {

    val scenario_1 = scenario("Macbook_Request")
      .exec(http("macbook_get_request")
        .get("/computers?f=macbook")
        .check(status.not(404), status.not(500))
        .check(status.is(200))
        //        .body(ElFileBody("ListAllEstates_Response_0001.txt")).asJson
        //        @wip.To enable the above line and save the response in folder add path in pom.xml configure
      )
  }

  object AllEstates {

    val scenario_1 = scenario("Get_Page_1")
      .exec(http("page_1_get_request")
        .get("/computers?p=1")
        .check(status.not(404), status.not(500))
        .check(status.is(200))
      )
  }

  object GetPlotPage {

    val scenario_1 = scenario("New_Computer_Page")
      .exec(http("New_Computer_Page_get_request")
        .get("/computers/new")
        .check(status.not(404), status.not(500))
        .check(status.is(200))
      )
  }

  val user_1 = scenario("Get_Page_1").exec(AllEstates.scenario_1)
  val user_2 = scenario("Macbook_Request").exec(Developer.scenario_1)
  val user_3 = scenario("New_Computer_Page").exec(GetPlotPage.scenario_1)

  setUp(
    user_1.inject(rampUsers(100) during (30 seconds)).protocols(httpProtocol),
    user_2.inject(rampUsers(100) during (30 seconds)).protocols(httpProtocol),
    user_3.inject(rampUsers(100) during (30 seconds)).protocols(httpProtocol))

}