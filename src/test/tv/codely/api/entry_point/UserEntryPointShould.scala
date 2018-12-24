package tv.codely.api.entry_point

import spray.json._
import akka.http.scaladsl.model._
import tv.codely.api.module.user.domain.{User, UserStub}
import tv.codely.api.module.user.infrastructure.marshaller.UserJsValueMarshaller
import tv.codely.api.module.shared.infrastructure.persistence.doobie.TypesConversions._
import doobie.implicits._
import cats.implicits._


final class UserSpec extends AcceptanceSpec {
  private val expectedUsers = UserStub.randomSeq

  override def beforeAll(): Unit = {
    super.beforeAll()

    def insertExpectedUsers() =
      Update[User]("INSERT INTO users(user_id, name) values (?, ?)")
      .updateMany(expectedUsers.toVector)
      .transact(doobieDbConnection.transactor)
      .unsafeToFuture()
      .futureValue

    insertExpectedUsers()
  }

  override def afterAll(): Unit = {
    super.afterAll()

    def truncateUsersTable() =
      sql"TRUNCATE TABLE users".update.run
      .transact(doobieDbConnection.transactor)
      .unsafeToFuture()
      .futureValue

    truncateUsersTable()
  }

  "return all the system users" in getting("/users") {
    val expectedUsers = Seq(
      UserStub(id = "deacd129-d419-4552-9bfc-0723c3c4f56a", name = "Edufasio"),
      UserStub(id = "b62f767f-7160-4405-a4af-39ebb3635c17", name = "Edonisio")
    )

    status shouldBe StatusCodes.OK
    contentType shouldBe ContentTypes.`application/json`
    entityAs[String].parseJson shouldBe UserJsValueMarshaller.marshall(expectedUsers)
  }
}
