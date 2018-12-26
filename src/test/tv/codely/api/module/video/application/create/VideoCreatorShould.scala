package tv.codely.api.module.video.application.create

import tv.codely.api.module.video.VideoUnitTestCase
import tv.codely.api.module.video.infrastructure.stub.VideoStub

final class VideoCreatorShould extends VideoUnitTestCase {
  private val creator = new VideoCreator(repository)

  "save a video" in {
    val video = VideoStub.random

    repositoryShouldSave(video)

    creator.create(video.id, video.title, video.duration, video.category) shouldBe ()
  }
}
