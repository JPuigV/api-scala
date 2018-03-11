package tv.codely.api

import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.Directives._
import tv.codely.api.user.domain.User
import spray.json.DefaultJsonProtocol
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import tv.codely.api.user.infrastructure.UserMarshaller._

object Routes extends SprayJsonSupport with DefaultJsonProtocol {

  private val systemUsers = Seq(
    User(id = "deacd129-d419-4552-9bfc-0723c3c4f56a", name = "Edufasio"),
    User(id = "b62f767f-7160-4405-a4af-39ebb3635c17", name = "Edonisio")
  )

  val all: Route = get {
    path("status") {
      complete(HttpEntity(ContentTypes.`application/json`, """{"status":"ok"}"""))
    } ~
    path("ping") {
      complete(HttpEntity(ContentTypes.`application/json`, """{"data":"pong"}"""))
    } ~
    path("users") {
      complete(systemUsers)
    }
  }
}
