package tv.codely.api.module.course.infrastructure.stub

import java.util.UUID

import tv.codely.api.module.course.domain.CourseId
import tv.codely.api.module.shared.domain.UuidStub

object CourseIdStub {
  def apply(value: String): CourseId = CourseIdStub(UuidStub(value))

  def apply(value: UUID): CourseId = CourseId(value)

  def random: CourseId = CourseId(UuidStub.random)
}
