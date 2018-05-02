package com.company.app.simulation.feeders

import com.company.app.feeder.ArrayFeeder
import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder

// https://gatling.io/docs/current/session/feeder/
class ArrayMapHasUuidTest extends Simulation {
  val feedCustomers: ScenarioBuilder = scenario("Feed customers")
    // feeder.queue: exception occurs when Feeder is empty
    //    .feed(CustomerFeeder.feeder.queue) // default behavior: use an Iterator on the underlying sequence
    //    .feed(CustomerFeeder.feeder.random) // randomly pick an entry in the sequence
    .feed(ArrayFeeder.feeder.circular) // go back to the top of the sequence once the end if reached
    .exec(session => {
    val uuid = session.get("uuid").asOption[Iterator[Map[String, String]]]
    val name = session.get("name").asOption[String]
    val age = session.get("age").asOption[String]

    println(uuid.get.next().get("uuid").get + " - " + name.get + " is " + age.get)

    session
  })

  setUp(
    feedCustomers.inject(
      atOnceUsers(5)
    )
  )

}