package tv.codely.api.entry_point

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import spray.json.DefaultJsonProtocol._
import spray.json.JsValue
import scala.concurrent.duration._

final class Routes(container: EntryPointDependencyContainer) {
  val all: Route =
    get {
      path("status")(container.statusGetController.get())  ~
      path("users")(container.userGetController.get()) ~
      path("videos")(container.videoGetController.get()) ~
      path("courses")(container.courseGetController.get())
    } ~
    post {
      path("videos") {
        jsonBody { body =>
          container.videoPostController.post(
            body("id").convertTo[String],
            body("title").convertTo[String],
            body("duration_in_seconds").convertTo[Int].seconds,
            body("category").convertTo[String]
          )
        }
      } ~
      path("courses") {
        jsonBody { body =>
          container.coursePostController.post(
            body("id").convertTo[String],
            body("title").convertTo[String],
            body("duration_in_seconds").convertTo[Int].seconds
          )
        }
      }
    }

  private def jsonBody[T](handler: Map[String, JsValue] => Route): Route =
    entity(as[JsValue])(json => handler(json.asJsObject.fields))
}
