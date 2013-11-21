package aprikot.cms.controllers

import org.scalatest._
import com.typesafe.scalalogging.slf4j.Logging
import play.api.test._
import play.api.test.Helpers._

class AplicationSpec extends WordSpec {
  "Application" should {
    "send 404 on a bad request" in {
      running(FakeApplication()) {
        assert(route(FakeRequest(GET, "/boum")) === None)
      }
    }
    "render the index page" in {
      running(FakeApplication()) {
        val home = route(FakeRequest(GET, "/")).get
        assert(status(home) === OK)
        assert(contentType(home) === Some("text/html"))
        assert(contentAsString(home).contains("Your new application is ready."))
      }
    }
  }
}