package tv.codely.api.entry_point

import tv.codely.api.entry_point.controller.status.StatusGetController
import tv.codely.api.entry_point.controller.user.UserGetController
import tv.codely.api.entry_point.controller.video.{VideoGetController, VideoPostController}
import tv.codely.api.module.user.infrastructure.dependency_injection.UserModuleDependencyContainer
import tv.codely.api.module.video.infrastructure.dependency_injection.VideoModuleDependencyContainer

final class EntryPointDependencyContainer(
    userDependencies: UserModuleDependencyContainer,
    videoDependencies: VideoModuleDependencyContainer
) {
  val statusGetController = new StatusGetController

  val userGetController = new UserGetController(userDependencies.usersSearcher)

  val videoGetController = new VideoGetController(videoDependencies.videosSearcher)
  val videoPostController = new VideoPostController(videoDependencies.videoCreator)
}
