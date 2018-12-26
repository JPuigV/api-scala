package tv.codely.api.module.course

import tv.codely.api.module.UnitTestCase
import tv.codely.api.module.course.domain.{Course, CourseRepository}

import scala.concurrent.Future

protected[course] trait CourseUnitTestCase extends UnitTestCase {
  protected val repository: CourseRepository = mock[CourseRepository]

  protected def repositoryShouldSave(course: Course): Unit =
    (repository.save _)
      .expects(course)
      .returning(Future.unit)

  protected def shouldSearchAllCourses(courses: Seq[Course]): Unit =
    (repository.all _)
      .expects()
      .returning(Future.successful(courses))
}
