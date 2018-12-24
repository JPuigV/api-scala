package tv.codely.api.module.video.application.search

import tv.codely.api.module.video.VideoUnitTestCase
import tv.codely.api.module.video.domain.Video
import tv.codely.api.module.video.infrastructure.stub.VideoStub

final class VideosSearcherTest extends VideoUnitTestCase {
  private val searcher = new VideosSearcher(repository)

  "Videos Searcher" should {
    "search all existing videos" in {
      val existingVideo = VideoStub.random
      val anotherExistingVideo = VideoStub.random
      val existingVideos: Seq[Video] = Seq(existingVideo, anotherExistingVideo)

      shouldSearchAllVideos(existingVideos)

      searcher.all().futureValue should be (existingVideos)
    }
  }
}
