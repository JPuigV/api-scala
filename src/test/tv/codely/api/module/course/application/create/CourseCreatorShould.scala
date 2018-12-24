package tv.codely.api.module.course.application.create

import tv.codely.api.module.course.CourseUnitTestCase
import tv.codely.api.module.course.infrastructure.stub.CourseStub

final class CourseCreatorTest extends CourseUnitTestCase {
  private val creator = new CourseCreator(repository)

  "Courses Creator" should {
    "save a course" in {
      val course = CourseStub.random

      repositoryShouldSave(course)

      creator.create(course.id, course.title, course.duration) should be(())
    }
  }
}
