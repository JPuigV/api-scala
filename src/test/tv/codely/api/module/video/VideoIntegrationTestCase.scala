package tv.codely.api.module.video

import tv.codely.api.module.IntegrationTestCase
import tv.codely.api.module.video.domain.VideoRepository
import tv.codely.api.module.video.infrastructure.dependency_injection.VideoModuleDependencyContainer

protected[video] trait VideoIntegrationTestCase extends IntegrationTestCase {
  private val container = new VideoModuleDependencyContainer(doobieDbConnection)

  protected val repository: VideoRepository = container.repository
}
