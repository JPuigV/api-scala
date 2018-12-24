package tv.codely.api.module.user.application

import tv.codely.api.module.user.UserUnitTestCase
import tv.codely.api.module.user.domain.UserStub

final class UsersSearcherTest extends UserUnitTestCase {
  private val searcher = new UsersSearcher(repository)

  "Users Searcher" should {
    "search all existing users" in {
      val existingUser = UserStub.random
      val anotherExistingUser = UserStub.random
      val existingUsers = Seq(existingUser, anotherExistingUser)

      shouldSearchAllUsers(existingUsers)

      searcher.all().futureValue should be (existingUsers)
    }
  }
}
