package tv.codely.api.entry_point.controller.status

import akka.http.scaladsl.server.StandardRoute
import akka.http.scaladsl.server.Directives.complete
import akka.http.scaladsl.model.{ContentTypes, HttpEntity}

final class StatusGetController {
  def get(): StandardRoute = complete(HttpEntity(ContentTypes.`application/json`, """{"status":"ok"}"""))
}
