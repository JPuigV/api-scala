package tv.codely.api.module.user

import tv.codely.api.module.IntegrationTestCase
import tv.codely.api.module.user.domain.UserRepository
import tv.codely.api.module.user.infrastructure.dependency_injection.UserModuleDependencyContainer

protected[user] trait UserIntegrationTestCase extends IntegrationTestCase {
  private val container = new UserModuleDependencyContainer(doobieDbConnection)

  protected val repository: UserRepository = container.repository
}
