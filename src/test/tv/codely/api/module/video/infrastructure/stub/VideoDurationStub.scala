package tv.codely.api.module.video.infrastructure.stub

import tv.codely.api.module.shared.infrastructure.stub.DurationStub
import tv.codely.api.module.video.domain.VideoDuration

object VideoDurationStub {
  def apply(value: BigDecimal): VideoDuration = VideoDuration(value)

  def random: VideoDuration = VideoDuration(DurationStub.random)
}
