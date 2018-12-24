package tv.codely.api.entry_point

import akka.http.scaladsl.model.{ContentTypes, StatusCodes}

final class StatusSpec extends AcceptanceSpec {
  "ScalaHttpApi" should {
    "respond successfully while requesting its status" in getting("/status") {
      status shouldBe StatusCodes.OK
      contentType shouldBe ContentTypes.`application/json`
      entityAs[String] shouldBe """{"status":"ok"}"""
    }
  }
}
