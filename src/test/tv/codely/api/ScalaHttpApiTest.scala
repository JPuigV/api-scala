package tv.codely.api

import akka.http.scaladsl.model._
import akka.http.scaladsl.testkit.ScalatestRouteTest
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.{Matchers, WordSpec}
import akka.http.scaladsl.server.Route
import tv.codely.api.user.infrastructure.marshaller.UserMarshaller
import tv.codely.api.user.infrastructure.stub.UserStub
import spray.json._

final class ScalaHttpApiTest extends WordSpec with Matchers with ScalaFutures with ScalatestRouteTest {

  "ScalaHttpApi" should {

    "respond successfully while requesting its status" in {
      Get("/status") ~> Routes.all ~> check {
        status shouldBe StatusCodes.OK
        contentType shouldBe ContentTypes.`application/json`
        entityAs[String] shouldBe """{"status":"ok"}"""
      }
    }

    "return pong while asking a ping" in {
      Get("/ping") ~> Routes.all ~> check {
        status shouldBe StatusCodes.OK
        contentType shouldBe ContentTypes.`application/json`
        entityAs[String] shouldBe """{"data":"pong"}"""
      }
    }

    "return all the system users" in {
      Get("/users") ~> Routes.all ~> check {
        val expectedUsers = Seq(
          UserStub(id = "deacd129-d419-4552-9bfc-0723c3c4f56a", name = "Edufasio"),
          UserStub(id = "b62f767f-7160-4405-a4af-39ebb3635c17", name = "Edonisio")
        )

        status shouldBe StatusCodes.OK
        contentType shouldBe ContentTypes.`application/json`
        entityAs[String].parseJson shouldBe UserMarshaller.marshall(expectedUsers)
      }
    }

    "return a MethodNotAllowed error for PUT requests to the root path" in {
      Put() ~> Route.seal(Routes.all) ~> check {
        status shouldEqual StatusCodes.MethodNotAllowed
        responseAs[String] shouldEqual "HTTP method not allowed, supported methods: GET"
      }
    }
  }
}