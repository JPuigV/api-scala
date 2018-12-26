package tv.codely.api.module.video.infrastructure.stub

import tv.codely.api.module.shared.domain.SeqStub
import tv.codely.api.module.video.domain.Video

import scala.concurrent.duration._

object VideoStub {
  def apply(
      id: String = VideoIdStub.random.value.toString,
      title: String = VideoTitleStub.random.value.toString,
      duration: Duration = VideoDurationStub.random.value,
      category: String = VideoCategoryStub.random.toString
  ) = Video(id, title, duration, category)

  def random: Video = apply()

  def randomSeq: Seq[Video] = SeqStub.randomOf(apply())
}
