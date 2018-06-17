package tv.codely.api.entry_point.controller.video

import akka.http.scaladsl.model.HttpResponse
import akka.http.scaladsl.model.StatusCodes.NoContent
import akka.http.scaladsl.server.Directives.complete
import akka.http.scaladsl.server.StandardRoute
import tv.codely.api.module.video.application.create.VideoCreator
import tv.codely.api.module.video.domain._

import scala.concurrent.duration.Duration

final class VideoPostController(creator: VideoCreator) {
  def post(id: String, title: String, duration: Duration, category: String): StandardRoute = {
    creator.create(VideoId(id), VideoTitle(title), VideoDuration(duration), VideoCategory(category))

    complete(HttpResponse(NoContent))
  }
}
