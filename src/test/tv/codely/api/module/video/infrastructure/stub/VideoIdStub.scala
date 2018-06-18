package tv.codely.api.module.video.infrastructure.stub

import java.util.UUID

import tv.codely.api.module.shared.infrastructure.stub.UuidStub
import tv.codely.api.module.video.domain.VideoId

object VideoIdStub {
  def apply(value: String): VideoId = VideoIdStub(UuidStub(value))

  def apply(value: UUID): VideoId = VideoId(value)

  def random: VideoId = VideoId(UuidStub.random)
}
