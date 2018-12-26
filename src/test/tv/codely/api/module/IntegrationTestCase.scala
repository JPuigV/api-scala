package tv.codely.api.module

import com.typesafe.config.ConfigFactory
import tv.codely.api.module.shared.infrastructure.config.DbConfig
import tv.codely.api.module.shared.infrastructure.dependency_injection.SharedModuleDependencyContainer
import tv.codely.api.module.shared.infrastructure.persistence.doobie.DoobieDbConnection

import scala.concurrent.ExecutionContext

protected[api] trait IntegrationTestCase extends UnitTestCase {
  private val appConfig       = ConfigFactory.load("application")
  private val dbConfig        = DbConfig(appConfig.getConfig("database"))
  private val actorSystemName = "scala-http-api-integration-test"

  private val sharedDependencies                            = new SharedModuleDependencyContainer(actorSystemName, dbConfig)
  implicit protected val executionContext: ExecutionContext = sharedDependencies.executionContext

  protected val doobieDbConnection: DoobieDbConnection = sharedDependencies.doobieDbConnection
}
