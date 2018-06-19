package tv.codely.api.module.video

import tv.codely.api.module.IntegrationTestCase
import tv.codely.api.module.video.infrastructure.dependency_injection.VideoModuleDependencyContainer
import tv.codely.api.module.video.infrastructure.repository.InMemoryVideoRepository

protected[video] trait VideoIntegrationTestCase extends IntegrationTestCase {
  private val container = new VideoModuleDependencyContainer

  protected val repository: InMemoryVideoRepository = container.repository
}
