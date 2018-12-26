package tv.codely.api.module.course.infrastructure.marshaller

import java.util.UUID

import spray.json.{DeserializationException, JsNumber, JsString, JsValue, JsonFormat, RootJsonFormat}
import spray.json.DefaultJsonProtocol._
import tv.codely.api.module.course.domain._

object CourseJsonFormatMarshaller {
  implicit object UuidMarshaller extends JsonFormat[UUID] {
    def write(value: UUID): JsValue = JsString(value.toString)

    def read(value: JsValue): UUID = value match {
      case JsString(uuid) => UUID.fromString(uuid)
      case _              => throw DeserializationException(s"Expected hexadecimal UUID string")
    }
  }

  implicit object CourseIdMarshaller extends JsonFormat[CourseId] {
    def write(value: CourseId): JsValue = JsString(value.value.toString)

    def read(value: JsValue): CourseId = value match {
      case JsString(id) => CourseId(id)
      case _            => throw DeserializationException(s"Expected 1 string for CourseId")
    }
  }

  implicit object CourseTitleMarshaller extends JsonFormat[CourseTitle] {
    def write(value: CourseTitle): JsValue = JsString(value.value)

    def read(value: JsValue): CourseTitle = value match {
      case JsString(title) => CourseTitle(title)
      case _               => throw DeserializationException(s"Expected 1 string for CourseTitle")
    }
  }

  implicit object CourseDurationMarshaller extends JsonFormat[CourseDuration] {
    def write(value: CourseDuration): JsValue = JsNumber(value.value.toSeconds)

    def read(value: JsValue): CourseDuration = value match {
      case JsNumber(seconds) => CourseDuration(seconds)
      case _                 => throw DeserializationException(s"Expected 1 string for CourseDuration")
    }
  }

  implicit val videoFormat: RootJsonFormat[Course] = jsonFormat(
    Course.apply(_: CourseId, _: CourseTitle, _: CourseDuration),
    "id",
    "title",
    "duration_in_seconds"
  )
}
