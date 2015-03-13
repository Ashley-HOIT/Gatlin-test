package default

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class RecordedSimulation extends Simulation {

	val httpProtocol = http
		.baseURL("http://computer-database.herokuapp.com")
		.inferHtmlResources(BlackList(""".*\.css""", """.*\.ico""", """.*\.js"""), WhiteList())
		.acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("en-US,en;q=0.5")
		.connection("keep-alive")
		.userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.10; rv:33.0) Gecko/20100101 Firefox/33.0")



    val uri1 = "http://computer-database.herokuapp.com"

	val scn = scenario("RecordedSimulation")
		// searcg
		.exec(http("request_0")
			.get("/"))
		.pause(12)
		.exec(http("request_1")
			.get("/computers?f=macbook"))
		.pause(2)
		.exec(http("request_2")
			.get("/computers/6"))
		.pause(1)
		.exec(http("request_3")
			.get("/computers"))
		.pause(20)
		// help
		.exec(http("request_4")
			.get("/computers/381"))
		.pause(1)
		.exec(http("request_5")
			.get("/computers"))

	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}