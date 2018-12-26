package tv.codely.api.module.course.domain

import scala.concurrent.duration._

object Course {
  def apply(id: String, title: String, duration: Duration): Course = Course(
    CourseId(id),
    CourseTitle(title),
    CourseDuration(duration)
  )
}

case class Course(id: CourseId, title: CourseTitle, duration: CourseDuration)
