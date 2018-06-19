package tv.codely.api.module.course.infrastructure.marshaller

import spray.json.{JsArray, JsNumber, JsObject, JsString}
import tv.codely.api.module.course.domain.Course

object CourseJsValueMarshaller {
  def marshall(courses: Seq[Course]): JsArray = JsArray(
    courses
      .map(
        v =>
          JsObject(
            "id"       -> JsString(v.id.value.toString),
            "title"    -> JsString(v.title.value),
            "duration" -> JsNumber(v.duration.value.toSeconds),
          ))
      .toVector
  )
}
