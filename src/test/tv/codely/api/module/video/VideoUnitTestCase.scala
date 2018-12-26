package tv.codely.api.module.video

import tv.codely.api.module.UnitTestCase
import tv.codely.api.module.video.domain.{Video, VideoRepository}

import scala.concurrent.Future

protected[video] trait VideoUnitTestCase extends UnitTestCase {
  protected val repository: VideoRepository = mock[VideoRepository]

  protected def repositoryShouldSave(video: Video): Unit =
    (repository.save _)
      .expects(video)
      .returning(Future.unit)

  protected def repositoryShouldFind(videos: Seq[Video]): Unit =
    (repository.all _)
      .expects()
      .returning(Future.successful(videos))
}
