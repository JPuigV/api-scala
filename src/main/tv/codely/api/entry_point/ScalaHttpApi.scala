package tv.codely.api.entry_point

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import com.typesafe.config.ConfigFactory
import tv.codely.api.module.course.infrastructure.dependency_injection.CourseModuleDependencyContainer
import tv.codely.api.module.user.infrastructure.dependency_injection.UserModuleDependencyContainer
import tv.codely.api.module.video.infrastructure.dependency_injection.VideoModuleDependencyContainer

import scala.concurrent.ExecutionContextExecutor
import scala.io.StdIn

object ScalaHttpApi {
  def main(args: Array[String]): Unit = {
    val appConfig = ConfigFactory.load("application")
    val serverConfig = ConfigFactory.load("http-server")

    val actorSystemName = appConfig.getString("main-actor-system.name")
    val host = serverConfig.getString("http-server.host")
    val port = serverConfig.getInt("http-server.port")

    implicit val system: ActorSystem = ActorSystem(actorSystemName)
    implicit val materializer: ActorMaterializer = ActorMaterializer()
    implicit val executionContext: ExecutionContextExecutor = system.dispatcher

    val container = new EntryPointDependencyContainer(
      new UserModuleDependencyContainer,
      new VideoModuleDependencyContainer,
      new CourseModuleDependencyContainer
    )

    val routes = new Routes(container)

    val bindingFuture = Http().bindAndHandle(routes.all, host, port)

    println(s"Server online at http://$host:$port/\nPress RETURN to stop...")
    StdIn.readLine()

    bindingFuture
      .flatMap(_.unbind())
      .onComplete(_ => system.terminate())
  }
}
