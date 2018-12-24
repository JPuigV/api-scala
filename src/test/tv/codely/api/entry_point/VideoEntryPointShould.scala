package tv.codely.api.entry_point

import scala.concurrent.duration._

import spray.json._
import akka.http.scaladsl.model.{ContentTypes, StatusCodes}
import tv.codely.api.module.video.infrastructure.stub.VideoStub
import tv.codely.api.module.video.infrastructure.marshaller.VideoJsValueMarshaller

final class VideoSpec extends AcceptanceSpec {
  "save a video" in posting(
    "/videos",
    """
      |{
      |  "id": "a11098af-d352-4cce-8372-2b48b97e6942",
      |  "title": "🎥 Entrevista a SergiGP, de troll a developer!",
      |  "duration_in_seconds": 15,
      |  "category": "Interview"
      |}
    """.stripMargin
  ) {
    status shouldBe StatusCodes.NoContent
  }

  "return all the system videos" in getting("/videos") {
    val expectedVideos = Seq(
      VideoStub(
        id = "a11098af-d352-4cce-8372-2b48b97e6942",
        title = "🎥 Entrevista a SergiGP, de troll a developer!",
        duration = 15.seconds,
        category = "Interview"
      )
    )

    status shouldBe StatusCodes.OK
    contentType shouldBe ContentTypes.`application/json`
    entityAs[String].parseJson shouldBe VideoJsValueMarshaller.marshall(expectedVideos)
  }
}
