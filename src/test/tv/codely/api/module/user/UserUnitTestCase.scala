package tv.codely.api.module.user

import tv.codely.api.module.UnitTestCase
import tv.codely.api.module.user.domain.{User, UserRepository}

protected[user] trait UserUnitTestCase extends UnitTestCase {
  protected val repository: UserRepository = mock[UserRepository]

  protected def shouldSearchAllUsers(users: Seq[User]): Unit =
    (repository.all _)
    .expects()
    .returning(users)
}
