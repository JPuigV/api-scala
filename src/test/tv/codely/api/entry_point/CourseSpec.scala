package tv.codely.api.entry_point

import scala.concurrent.duration._

import spray.json._
import akka.http.scaladsl.model.{ContentTypes, StatusCodes}
import tv.codely.api.module.course.infrastructure.marshaller.CourseJsValueMarshaller
import tv.codely.api.module.course.infrastructure.stub.CourseStub

final class CourseSpec extends AcceptanceSpec {
  "save a course" in post(
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

  "return all the system courses" in get("/courses") {
    val expectedCourses = Seq(
      CourseStub(
        id = "a11098af-d352-4cce-8372-2b48b97e6942",
        title = "Curso de Spermis!",
        duration = 90.seconds
      )
    )

    status shouldBe StatusCodes.OK
    contentType shouldBe ContentTypes.`application/json`
    entityAs[String].parseJson shouldBe CourseJsValueMarshaller.marshall(expectedCourses)
  }
}
