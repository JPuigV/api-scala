package tv.codely.api.module.video.infrastructure.repository

import doobie.implicits._
import tv.codely.api.module.video.VideoIntegrationTestCase
import tv.codely.api.module.video.infrastructure.stub.VideoStub

final class DoobieMySqlVideoRepositoryShould extends VideoIntegrationTestCase {
  private def cleanVideosTable() =
    sql"TRUNCATE TABLE videos".update.run
      .transact(doobieDbConnection.transactor)
      .unsafeToFuture()
      .futureValue

  "return an empty sequence if there're no videos" in {
    cleanVideosTable()

    repository.all().futureValue shouldBe Seq.empty
  }

  "search all existing videos" in {
    cleanVideosTable()

    val videos = VideoStub.randomSeq

    videos.foreach(v => repository.save(v).futureValue)

    repository.all().futureValue shouldBe videos
  }
}
