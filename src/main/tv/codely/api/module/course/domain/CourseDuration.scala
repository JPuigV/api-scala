package tv.codely.api.module.course.domain

import scala.concurrent.duration._

object CourseDuration {
  def apply(seconds: BigDecimal): CourseDuration = CourseDuration(seconds.longValue().seconds)
}

case class CourseDuration(value: Duration)
