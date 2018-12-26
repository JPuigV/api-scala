package tv.codely.api.module.shared.domain

import scala.concurrent.duration._

object DurationStub {
  def random: Duration = IntStub.randomUnsigned().seconds
}
