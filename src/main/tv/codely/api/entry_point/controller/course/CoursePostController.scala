package tv.codely.api.entry_point.controller.course

import akka.http.scaladsl.model.HttpResponse
import akka.http.scaladsl.model.StatusCodes.NoContent
import akka.http.scaladsl.server.Directives.complete
import akka.http.scaladsl.server.StandardRoute
import tv.codely.api.module.course.application.create.CourseCreator
import tv.codely.api.module.course.domain.{CourseDuration, CourseId, CourseTitle}

import scala.concurrent.duration.Duration

final class CoursePostController(creator: CourseCreator) {
  def post(id: String, title: String, duration: Duration): StandardRoute = {
    creator.create(CourseId(id), CourseTitle(title), CourseDuration(duration))

    complete(HttpResponse(NoContent))
  }
}
