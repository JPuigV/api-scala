package tv.codely.api.module.course.application.search

import tv.codely.api.module.course.domain.{Course, CourseRepository}

final class CoursesSearcher(repository: CourseRepository) {
  def all(): Seq[Course] = repository.all()
}
