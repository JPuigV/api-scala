package tv.codely.api.module.video.infrastructure.stub

import tv.codely.api.module.video.domain.VideoCategory

import scala.util.Random

object VideoCategoryStub {
  private val options: Seq[String] = Seq("Screencast","Interview")
  def apply(value: String): VideoCategory = VideoCategory(value)

  def random: VideoCategory = VideoCategory(Random.shuffle(options.toList).head)
}
