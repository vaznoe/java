package com.company.app

import com.company.app.feeder.UuidFeeder
import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef._

import scala.concurrent.duration._

class PostGetTest extends Simulation {

  val port: String = "8080"
  println(port)

  val postGet: ScenarioBuilder = scenario("Post Get")
    .feed(UuidFeeder.feeder)
    .exec(http("Create whisky")
      .post(s"http://localhost:${port}/api/whiskies")
      // $${uuid} is from UuidFeeder.feeder
      .body(StringBody(s"""{"name": "$${uuid}", "origin": "Scotland"}""")).asJSON
      .check(
        status.is(201),
        jsonPath("$..id").find.saveAs("id"),
        bodyString.saveAs("postResponseBody"))
    )
    .exec(session => {
      println("# Create whisky session")
      val id = session.get("id").asOption[String]
      println(id.get)
      val postResponseBody = session.get("postResponseBody").asOption[String]
      println(postResponseBody)
      session
    })
    .pause(1)
    .exec(http("Get whisky")
      .get(s"http://localhost:${port}/api/whiskies" + "/${id}")
      .check(
        status.is(200),
        bodyString.saveAs("getResponseBody")
      )
    )
    .exec(session => {
      println("# Get whisky session")
      val id = session.get("id").asOption[String]
      println(id.get)
      val getResponseBody = session.get("getResponseBody").asOption[String]
      println(getResponseBody)
      session
    })

  setUp(
    postGet.inject(atOnceUsers(1))
//      postGet.inject(constantUsersPerSec(50) during( 10 seconds))
  )

}