
import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class herokuapp extends Simulation {

	val httpProtocol = http
		.baseURL("http://computer-database.herokuapp.com")
		.inferHtmlResources(BlackList(""".*\.js""", """.*\.css""", """.*\.ico"""), WhiteList())
		.acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("en-US,en;q=0.5")
		.connection("keep-alive")
		.contentTypeHeader("application/x-www-form-urlencoded")
		.userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.10; rv:33.0) Gecko/20100101 Firefox/33.0")



    val Home = "http://computer-database.herokuapp.com"

    val users = scenario("Users").exec(Search.search, Browse.browse)
    val admins = scenario("Admins").exec(Search.search, Browse.browse, Edit.edit)


	setUp(
	users.inject(rampUsers(10) over (10 seconds)),
	admins.inject(rampUsers(2) over (10 seconds))

	.protocols(httpProtocol)
}