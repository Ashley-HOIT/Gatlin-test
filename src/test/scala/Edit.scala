object Edit {

.exec(http("request_10")
			.get("/computers/new"))
		.pause(31)
		.exec(http("Edit 1")
			.post("/computers")
			.formParam("name", "Macbook Pro")
			.formParam("introduced", "Mid 2012")
			.formParam("discontinued", "July 2014")
			.formParam("company", "1")
			.check(status.is(400)))
		.pause(30)
		.exec(http("Edit 2")
			.post("/computers")
			.formParam("name", "Macbook Pro")
			.formParam("introduced", "2012-07-01")
			.formParam("discontinued", "2014-07-01")
			.formParam("company", "1"))

}