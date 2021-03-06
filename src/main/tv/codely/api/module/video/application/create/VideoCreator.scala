package tv.codely.api.module.video.application.create

import tv.codely.api.module.video.domain._

final class VideoCreator(repository: VideoRepository) {
  def create(id: VideoId, title: VideoTitle, duration: VideoDuration, category: VideoCategory): Unit = {
    val video = Video(id, title, duration, category)

    repository.save(video)
  }
}
