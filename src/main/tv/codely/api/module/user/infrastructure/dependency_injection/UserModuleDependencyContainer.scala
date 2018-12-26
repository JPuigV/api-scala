package tv.codely.api.module.user.infrastructure.dependency_injection

import tv.codely.api.module.shared.infrastructure.persistence.doobie.DoobieDbConnection
import tv.codely.api.module.user.application.UsersSearcher
import tv.codely.api.module.user.application.register.UserRegisterer
import tv.codely.api.module.user.domain.UserRepository
import tv.codely.api.module.user.infrastructure.repository.DoobieMySqlUserRepository

import scala.concurrent.ExecutionContext

final class UserModuleDependencyContainer
    (doobieDbConnection: DoobieDbConnection)
    (implicit executionContext: ExecutionContext) {
  val repository: UserRepository = new DoobieMySqlUserRepository(doobieDbConnection)

  val usersSearcher: UsersSearcher = new UsersSearcher(repository)
  val userRegisterer: UserRegisterer = new UserRegisterer(repository)
}
