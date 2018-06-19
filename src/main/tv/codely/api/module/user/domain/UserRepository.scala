package tv.codely.api.module.user.domain

trait UserRepository {
  def all(): Seq[User]
}
