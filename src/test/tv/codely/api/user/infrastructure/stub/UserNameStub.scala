package tv.codely.api.user.infrastructure.stub

import tv.codely.api.shared.infrastructure.stub.{IntStub, StringStub}
import tv.codely.api.user.domain.UserName

object UserNameStub {
  private val minimumChars = 1
  private val maximumChars = 20

  def apply(value: String): UserName = UserName(value)

  def random: UserName = UserName(
    StringStub.random(numChars = IntStub.randomBetween(minimumChars, maximumChars))
  )
}
