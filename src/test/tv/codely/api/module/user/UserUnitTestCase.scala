package tv.codely.api.module.user

import tv.codely.api.module.UnitTestCase
import tv.codely.api.module.user.domain.{User, UserRepository}

import scala.concurrent.Future

protected[user] trait UserUnitTestCase extends UnitTestCase {
  protected val repository: UserRepository = mock[UserRepository]

  protected def repositorysShouldSave(user: User): Unit =
    (repository.save _)
      .expects(user)
      .returning(Future.unit)

  protected def repositoryShouldFind(users: Seq[User]): Unit =
    (repository.all _)
      .expects()
      .returning(Future.successful(users))
}
