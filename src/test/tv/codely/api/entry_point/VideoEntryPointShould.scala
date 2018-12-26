package tv.codely.api.entry_point

import doobie.implicits._
import akka.http.scaladsl.model.{ContentTypes, StatusCodes}
import spray.json._
import tv.codely.api.module.video.infrastructure.stub.VideoStub
import tv.codely.api.module.video.infrastructure.marshaller.VideoJsValueMarshaller

final class VideoEntryPointShould extends AcceptanceSpec {
  private def cleanVideosTable() =
    sql"TRUNCATE TABLE videos".update.run
      .transact(doobieDbConnection.transactor)
      .unsafeToFuture()
      .futureValue

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

  "return all the videos" in getting("/videos") {
    cleanVideosTable()

    val videos = VideoStub.randomSeq

    videos.foreach(v => videoDependencies.repository.save(v).futureValue)

    getting("/videos") {
      status shouldBe StatusCodes.OK
      contentType shouldBe ContentTypes.`application/json`
      entityAs[String].parseJson shouldBe VideoJsValueMarshaller.marshall(videos)
    }
  }
}
