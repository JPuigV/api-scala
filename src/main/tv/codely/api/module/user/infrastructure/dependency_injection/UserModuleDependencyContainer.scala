package tv.codely.api.module.user.infrastructure.dependency_injection

import tv.codely.api.module.user.application.UsersSearcher
import tv.codely.api.module.user.infrastructure.repository.InMemoryUserRepository

final class UserModuleDependencyContainer {
  val repository = new InMemoryUserRepository

  val usersSearcher = new UsersSearcher(repository)
}
