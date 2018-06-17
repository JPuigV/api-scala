package tv.codely.api.module.user.infrastructure.stub

import java.util.UUID

import tv.codely.api.module.user.domain.UserId
import tv.codely.api.module.shared.infrastructure.stub.UuidStub

object UserIdStub {
  def apply(value: String): UserId = UserIdStub(UuidStub(value))

  def apply(value: UUID): UserId = UserId(value)

  def random: UserId = UserId(UuidStub.random)
}
