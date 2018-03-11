package tv.codely.api.user.infrastructure.stub

import java.util.UUID

import tv.codely.api.shared.infrastructure.stub.UuidStub
import tv.codely.api.user.domain.UserId

object UserIdStub {
  def apply(value: String): UserId = UserIdStub(UuidStub(value))

  def apply(value: UUID): UserId = UserId(value)

  def random: UserId = UserId(UuidStub.random)
}
