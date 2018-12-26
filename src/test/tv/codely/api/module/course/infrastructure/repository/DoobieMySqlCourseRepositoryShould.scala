package tv.codely.api.module.course.infrastructure.repository

import doobie.implicits._
import tv.codely.api.module.course.CourseIntegrationTestCase
import tv.codely.api.module.course.infrastructure.stub.CourseStub

final class DoobieMySqlCourseRepositoryShould extends CourseIntegrationTestCase {
  private def cleanCoursesTable() =
    sql"TRUNCATE TABLE courses".update.run
      .transact(doobieDbConnection.transactor)
      .unsafeToFuture()
      .futureValue

  "return an empty sequence if there're no courses" in {
    cleanCoursesTable()

    repository.all().futureValue shouldBe Seq.empty
  }

  "search all existing courses" in {
    cleanCoursesTable()

    val courses = CourseStub.randomSeq

    courses.foreach(v => repository.save(v).futureValue)

    repository.all().futureValue shouldBe courses
  }

}
