package test

import org.scalatest._
import com.typesafe.scalalogging.slf4j.Logging
import play.api.test._
import play.api.test.Helpers._

// This spec should be in "IntegrationTest" scope in sbt, but in Play, I think that requires a bit more work...
class IntegrationSpec extends WordSpec {
  "Application" should {
    "work from within a browser" in {
      running(TestServer(3333), HTMLUNIT) { browser =>
        browser.goTo("http://localhost:3333/")
        assert(browser.pageSource.contains("Your new application is ready."))
      }
    }
  }
}