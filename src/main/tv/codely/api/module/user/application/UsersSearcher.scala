package tv.codely.api.module.user.application

import tv.codely.api.module.user.domain.{User, UserRepository}

final class UsersSearcher(repository: UserRepository) {
  def all(): Seq[User] = repository.all()
}
