package tv.codely.api.entry_point

import doobie.implicits._
import spray.json._
import akka.http.scaladsl.model.{ContentTypes, StatusCodes}
import tv.codely.api.module.course.infrastructure.marshaller.CourseJsValueMarshaller
import tv.codely.api.module.course.infrastructure.stub.CourseStub

final class CourseEntryPointShould extends AcceptanceSpec {
  private def cleanCoursesTable() =
    sql"TRUNCATE TABLE courses".update.run
      .transact(doobieDbConnection.transactor)
      .unsafeToFuture()
      .futureValue

  "save a course" in posting(
    "/courses",
    """
      |{
      |  "id": "a11098af-d352-4cce-8372-2b48b97e6942",
      |  "title": "Curso de Spermis!",
      |  "duration_in_seconds": 90
      |}
    """.stripMargin
  ) {
    status shouldBe StatusCodes.NoContent
  }

  "return all the system courses" in getting("/courses") {
    cleanCoursesTable()

    val courses = CourseStub.randomSeq

    courses.foreach(v => courseDependencies.repository.save(v).futureValue)

    getting("/courses") {
      status shouldBe StatusCodes.OK
      contentType shouldBe ContentTypes.`application/json`
      entityAs[String].parseJson shouldBe CourseJsValueMarshaller.marshall(courses)
    }
  }
}
