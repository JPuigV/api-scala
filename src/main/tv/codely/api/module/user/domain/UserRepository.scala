package tv.codely.api.module.user.domain

import scala.concurrent.Future

trait UserRepository {
  def all(): Future[Seq[User]]

  def save(user: User): Future[Unit]
}
