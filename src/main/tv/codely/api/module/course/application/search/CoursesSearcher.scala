package tv.codely.api.module.course.application.search

import tv.codely.api.module.course.domain.{Course, CourseRepository}

import scala.concurrent.Future

final class CoursesSearcher(repository: CourseRepository) {
  def all(): Future[Seq[Course]] = repository.all()
}
