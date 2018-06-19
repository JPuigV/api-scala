package tv.codely.api.module.video.infrastructure.repository

import tv.codely.api.module.video.VideoIntegrationTestCase
import tv.codely.api.module.video.infrastructure.stub.VideoStub

final class InMemoryVideoRepositoryTest extends VideoIntegrationTestCase {
  "In memory video repository" should {
    "search all existing videos" in {
      val video        = VideoStub.random
      val anotherVideo = VideoStub.random
      val videos       = Seq(video, anotherVideo)

      repository.save(video)
      repository.save(anotherVideo)

      repository.all() should be(videos)
    }
  }
}
