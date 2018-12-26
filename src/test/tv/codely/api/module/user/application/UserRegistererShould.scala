package tv.codely.api.module.user.application

import tv.codely.api.module.user.UserUnitTestCase
import tv.codely.api.module.user.application.register.UserRegisterer
import tv.codely.api.module.user.domain.UserStub

final class UserRegistererShould extends UserUnitTestCase {
  private val registerer = new UserRegisterer(repository)

  "save a user" in {
    val user = UserStub.random

    repositorysShouldSave(user)

    registerer.register(user.id, user.name) shouldBe ()
  }
}
