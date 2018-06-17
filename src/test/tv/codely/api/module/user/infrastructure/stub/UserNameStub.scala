package tv.codely.api.module.user.infrastructure.stub

import tv.codely.api.module.user.domain.UserName
import tv.codely.api.module.shared.infrastructure.stub.{IntStub, StringStub}

object UserNameStub {
  private val minimumChars = 1
  private val maximumChars = 20

  def apply(value: String): UserName = UserName(value)

  def random: UserName = UserName(
    StringStub.random(numChars = IntStub.randomBetween(minimumChars, maximumChars))
  )
}
