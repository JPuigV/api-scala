package tv.codely.api.module.course.infrastructure.stub

import tv.codely.api.module.course.domain.CourseTitle
import tv.codely.api.module.shared.infrastructure.stub.{IntStub, StringStub}

object CourseTitleStub {
  private val minimumChars = 5
  private val maximumChars = 20

  def apply(value: String): CourseTitle = CourseTitle(value)

  def random: CourseTitle = CourseTitle(
    StringStub.random(numChars = IntStub.randomBetween(minimumChars, maximumChars))
  )
}
