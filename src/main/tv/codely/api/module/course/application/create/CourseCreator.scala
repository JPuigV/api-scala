package tv.codely.api.module.course.application.create

import tv.codely.api.module.course.domain._

final class CourseCreator(repository: CourseRepository) {
  def create(id: CourseId, title: CourseTitle, duration: CourseDuration): Unit = {
    val course = Course(id, title, duration)

    repository.save(course)
  }
}
