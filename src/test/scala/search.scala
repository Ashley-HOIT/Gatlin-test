object Search {

val feeder = csv("search.csv").random


val search = exec(http("Home")

	.get("/"))
		.pause(13)
		.feed(feeder)
		.exec(http("Search")
			.get("/computers?f=macbook"))
			.queryParam("f", "${searchCriterion}")
			.check(regex("""<a href="([^"]+)">${searchComputerName}</a>""").saveAs("url")))
		.pause(6)
		.exec(http("Select")
			.get("${url}"))
		.pause(12)
}