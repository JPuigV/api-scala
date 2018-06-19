package tv.codely.api.module.course

import tv.codely.api.module.IntegrationTestCase
import tv.codely.api.module.course.infrastructure.dependency_injection.CourseModuleDependencyContainer
import tv.codely.api.module.course.infrastructure.repository.InMemoryCourseRepository

protected[course] trait CourseIntegrationTestCase extends IntegrationTestCase {
  private val container = new CourseModuleDependencyContainer

  protected val repository: InMemoryCourseRepository = container.repository
}
