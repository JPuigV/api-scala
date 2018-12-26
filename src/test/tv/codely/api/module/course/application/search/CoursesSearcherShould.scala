package tv.codely.api.module.course.application.search

import tv.codely.api.module.course.CourseUnitTestCase
import tv.codely.api.module.course.domain.Course
import tv.codely.api.module.course.infrastructure.stub.CourseStub

final class CoursesSearcherShould extends CourseUnitTestCase {
  private val searcher = new CoursesSearcher(repository)

  "search all existing courses" in {
    val existingCourse               = CourseStub.random
    val anotherExistingCourse        = CourseStub.random
    val existingCourses: Seq[Course] = Seq(existingCourse, anotherExistingCourse)

    shouldSearchAllCourses(existingCourses)

    searcher.all().futureValue should be(existingCourses)
  }
}
