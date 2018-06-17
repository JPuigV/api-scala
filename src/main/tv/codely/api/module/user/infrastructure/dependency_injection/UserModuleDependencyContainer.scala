package tv.codely.api.module.user.infrastructure.dependency_injection

import tv.codely.api.module.user.application.UsersSearcher

final class UserModuleDependencyContainer {
  val usersSearcher = new UsersSearcher
}
