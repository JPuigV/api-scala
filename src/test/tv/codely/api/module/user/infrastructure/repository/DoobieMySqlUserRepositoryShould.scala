package tv.codely.api.module.user.infrastructure.repository

import doobie.implicits._
import tv.codely.api.module.user.UserIntegrationTestCase
import tv.codely.api.module.user.domain.UserStub

final class DoobieMySqlUserRepositoryShould extends UserIntegrationTestCase {
  private def cleanUsersTable() =
    sql"TRUNCATE TABLE users".update.run
      .transact(doobieDbConnection.transactor)
      .unsafeToFuture()
      .futureValue

  "return an empty sequence if there're no users" in {
    cleanUsersTable()

    repository.all().futureValue shouldBe Seq.empty
  }

  "search all existing users" in {
    cleanUsersTable()

    val users = UserStub.randomSeq

    users.foreach(u => repository.save(u).futureValue)

    repository.all().futureValue shouldBe users
  }
}
