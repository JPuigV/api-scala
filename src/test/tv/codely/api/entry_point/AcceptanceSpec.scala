package tv.codely.api.entry_point

import akka.http.scaladsl.model.{HttpEntity, HttpMethods, HttpRequest, MediaTypes}
import akka.http.scaladsl.testkit.ScalatestRouteTest
import akka.util.ByteString
import com.typesafe.config.ConfigFactory
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.{Matchers, WordSpec}
import tv.codely.api.module.course.infrastructure.dependency_injection.CourseModuleDependencyContainer
import tv.codely.api.module.shared.infrastructure.config.DbConfig
import tv.codely.api.module.shared.infrastructure.dependency_injection.SharedModuleDependencyContainer
import tv.codely.api.module.shared.infrastructure.persistence.doobie.DoobieDbConnection
import tv.codely.api.module.user.infrastructure.dependency_injection.UserModuleDependencyContainer
import tv.codely.api.module.video.infrastructure.dependency_injection.VideoModuleDependencyContainer

protected[entry_point] abstract class AcceptanceSpec
    extends WordSpec
    with Matchers
    with ScalaFutures
    with ScalatestRouteTest {
  private val appConfig                                = ConfigFactory.load("application")
  private val dbConfig                                 = DbConfig(appConfig.getConfig("database"))
  private val actorSystemName                          = "scala-http-api-acceptance-test"
  private val sharedDependencies                       = new SharedModuleDependencyContainer(actorSystemName, dbConfig)
  protected val doobieDbConnection: DoobieDbConnection = sharedDependencies.doobieDbConnection
  protected val userDependencies =
    new UserModuleDependencyContainer(doobieDbConnection)(sharedDependencies.executionContext)
  protected val videoDependencies =
    new VideoModuleDependencyContainer(doobieDbConnection)(sharedDependencies.executionContext)
  protected val courseDependencies =
    new CourseModuleDependencyContainer(doobieDbConnection)(sharedDependencies.executionContext)
  private val routes = new Routes(
    new EntryPointDependencyContainer(
      userDependencies,
      videoDependencies,
      courseDependencies
    )
  )

  protected def getting[T](path: String)(body: ⇒ T): T = Get(path) ~> routes.all ~> check(body)

  protected def posting[T](path: String, request: String)(body: ⇒ T): T =
    HttpRequest(
      method = HttpMethods.POST,
      uri = path,
      entity = HttpEntity(
        MediaTypes.`application/json`,
        ByteString(request)
      )
    ) ~> routes.all ~> check(body)
}
