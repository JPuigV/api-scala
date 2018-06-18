package tv.codely.api.module.video

import tv.codely.api.module.UnitTestCase
import tv.codely.api.module.video.domain.{Video, VideoRepository}

protected[video] trait VideoUnitTestCase extends UnitTestCase {
  protected val repository: VideoRepository = mock[VideoRepository]

  protected def repositoryShouldSave(video: Video): Unit =
    (repository.save _)
      .expects(video)
      .returning(())

  protected def shouldSearchAllVideos(videos: Seq[Video]): Unit =
    (repository.all _)
    .expects()
    .returning(videos)
}
