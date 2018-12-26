package tv.codely.api.module.video.infrastructure.stub

import tv.codely.api.module.shared.domain.{IntStub, StringStub}
import tv.codely.api.module.video.domain.VideoTitle

object VideoTitleStub {
  private val minimumChars = 5
  private val maximumChars = 20

  def apply(value: String): VideoTitle = VideoTitle(value)

  def random: VideoTitle = VideoTitle(
    StringStub.random(numChars = IntStub.randomBetween(minimumChars, maximumChars))
  )
}
