package tv.codely.api.module.shared.infrastructure.stub

import scala.concurrent.duration._

object DurationStub {
  def random: Duration = IntStub.randomUnsigned().seconds
}
