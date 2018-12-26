package tv.codely.api.module.user.application

import tv.codely.api.module.user.domain.{User, UserRepository}

import scala.concurrent.Future

final class UsersSearcher(repository: UserRepository) {
  def all(): Future[Seq[User]] = repository.all()
}
