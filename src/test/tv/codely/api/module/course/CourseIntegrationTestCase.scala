package tv.codely.api.module.course

import tv.codely.api.module.IntegrationTestCase
import tv.codely.api.module.course.domain.CourseRepository
import tv.codely.api.module.course.infrastructure.dependency_injection.CourseModuleDependencyContainer

protected[course] trait CourseIntegrationTestCase extends IntegrationTestCase {
  private val container = new CourseModuleDependencyContainer(doobieDbConnection)

  protected val repository: CourseRepository = container.repository
}
