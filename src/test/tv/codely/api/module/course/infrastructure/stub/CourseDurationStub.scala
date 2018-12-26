package tv.codely.api.module.course.infrastructure.stub

import tv.codely.api.module.course.domain.CourseDuration
import tv.codely.api.module.shared.domain.DurationStub

object CourseDurationStub {
  def apply(value: BigDecimal): CourseDuration = CourseDuration(value)

  def random: CourseDuration = CourseDuration(DurationStub.random)
}
