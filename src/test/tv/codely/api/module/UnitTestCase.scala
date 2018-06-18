package tv.codely.api.module

import org.scalatest.{Matchers, WordSpec}
import org.scalamock.scalatest.MockFactory

protected[module] trait UnitTestCase extends WordSpec with Matchers with MockFactory {

}
