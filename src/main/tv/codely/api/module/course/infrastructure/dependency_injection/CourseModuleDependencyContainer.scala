package tv.codely.api.module.course.infrastructure.dependency_injection

import tv.codely.api.module.course.application.create.CourseCreator
import tv.codely.api.module.course.application.search.CoursesSearcher
import tv.codely.api.module.course.infrastructure.repository.DoobieMySqlCourseRepository
import tv.codely.api.module.shared.infrastructure.persistence.doobie.DoobieDbConnection

import scala.concurrent.ExecutionContext

final class CourseModuleDependencyContainer(doobieDbConnection: DoobieDbConnection)(
    implicit executionContext: ExecutionContext) {
  val repository = new DoobieMySqlCourseRepository(doobieDbConnection)

  val coursesSearcher = new CoursesSearcher(repository)
  val courseCreator   = new CourseCreator(repository)
}
