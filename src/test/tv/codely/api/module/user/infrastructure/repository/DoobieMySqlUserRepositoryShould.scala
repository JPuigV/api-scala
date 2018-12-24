package tv.codely.api.module.user.infrastructure.repository

import doobie.implicits._
import doobie.util.update.Update
import tv.codely.api.module.user.UserIntegrationTestCase
import tv.codely.api.module.user.domain.{User, UserStub}

final class DoobieMySqlUserRepositoryTest extends UserIntegrationTestCase {
  private def insert(users: Seq[User]) =
    Update[User]("INSERT INTO users(user_id, name) values (?, ?)")
      .updateMany(users.toVector)
      .transact(doobieDbConnection.transactor)
      .unsafeToFuture()
      .futureValue

  private def cleanUsersTable() =
    sql"TRUNCATE TABLE users".update.run
      .transact(doobieDbConnection.transactor)
      .unsafeToFuture()
      .futureValue

  "Doobie MySql user repository" should {
    "return an empty sequence if there're no users" in {
      repository.all().futureValue shouldBe Seq.empty
    }

    "search all existing users" in {
      val expectedUsers = UserStub.randomSeq

      insert(expectedUsers)

      repository.all().futureValue should be(expectedUsers)

      cleanUsersTable()
    }
  }
}
