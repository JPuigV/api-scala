package tv.codely.api.module.video.application.create

import tv.codely.api.module.video.VideoUnitTestCase
import tv.codely.api.module.video.infrastructure.stub.VideoStub

final class VideoCreatorTest extends VideoUnitTestCase {
  private val creator = new VideoCreator(repository)

  "Videos Creator" should {
    "save a video" in {
      val video = VideoStub.random

      repositoryShouldSave(video)

      creator.create(video.id, video.title, video.duration, video.category) should be(())
    }
  }
}
