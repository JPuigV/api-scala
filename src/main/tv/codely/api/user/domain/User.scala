package tv.codely.api.user.domain

object User {
  def apply(id: String, name: String): User = User(UserId(id), UserName(name))
}

case class User(id: UserId, name: UserName)
