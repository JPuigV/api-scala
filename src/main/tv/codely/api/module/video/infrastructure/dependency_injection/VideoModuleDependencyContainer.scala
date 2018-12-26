package tv.codely.api.module.video.infrastructure.dependency_injection

import tv.codely.api.module.shared.infrastructure.persistence.doobie.DoobieDbConnection
import tv.codely.api.module.video.application.create.VideoCreator
import tv.codely.api.module.video.application.search.VideosSearcher
import tv.codely.api.module.video.domain.VideoRepository
import tv.codely.api.module.video.infrastructure.repository.DoobieMySqlVideoRepository

import scala.concurrent.ExecutionContext

final class VideoModuleDependencyContainer(
    doobieDbConnection: DoobieDbConnection
)(implicit executionContext: ExecutionContext) {
  val repository: VideoRepository = new DoobieMySqlVideoRepository(doobieDbConnection)

  val videosSearcher = new VideosSearcher(repository)
  val videoCreator   = new VideoCreator(repository)
}
