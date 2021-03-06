package tv.codely.api.module.course.infrastructure.stub

import tv.codely.api.module.course.domain.Course
import tv.codely.api.module.shared.domain.SeqStub

import scala.concurrent.duration.Duration

object CourseStub {
  def apply(
      id: String = CourseIdStub.random.value.toString,
      title: String = CourseTitleStub.random.value.toString,
      duration: Duration = CourseDurationStub.random.value
  ) = Course(id, title, duration)

  def random: Course = apply()

  def randomSeq: Seq[Course] = SeqStub.randomOf(apply())
}
