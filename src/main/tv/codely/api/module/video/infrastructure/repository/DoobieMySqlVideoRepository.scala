package tv.codely.api.module.video.infrastructure.repository

import doobie.implicits._
import tv.codely.api.module.shared.infrastructure.persistence.doobie.DoobieDbConnection
import tv.codely.api.module.video.domain.{Video, VideoRepository}
import tv.codely.api.module.shared.infrastructure.persistence.doobie.TypesConversions._

import scala.concurrent.{ExecutionContext, Future}

final class DoobieMySqlVideoRepository(db: DoobieDbConnection)(implicit executionContext: ExecutionContext)
    extends VideoRepository {
  override def all(): Future[Seq[Video]] =
    db.read(sql"SELECT video_id, title, duration_in_seconds, category FROM videos".query[Video].to[Seq])

  override def save(video: Video): Future[Unit] =
    sql"INSERT INTO videos (video_id, title, duration_in_seconds, category) VALUES (${video.id}, ${video.title}, ${video.duration}, ${video.category})".update.run
      .transact(db.transactor)
      .unsafeToFuture()
      .map(_ => ())
}
