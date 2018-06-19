package tv.codely.api.module.course.infrastructure.repository

import tv.codely.api.module.course.CourseIntegrationTestCase
import tv.codely.api.module.course.infrastructure.stub.CourseStub

final class InMemoryCourseRepositoryTest extends CourseIntegrationTestCase {
  "In memory course repository" should {
    "search all existing courses" in {
      val course        = CourseStub.random
      val anotherCourse = CourseStub.random
      val courses       = Seq(course, anotherCourse)

      repository.save(course)
      repository.save(anotherCourse)

      repository.all() should be(courses)
    }
  }
}
