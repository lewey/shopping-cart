package uk.gov.hmrc.shoppingCart

import org.mockito.Mockito
import org.scalatest.BeforeAndAfterEach
import org.scalatest.wordspec.AnyWordSpec

import scala.reflect.ClassTag

trait BaseSpec extends AnyWordSpec with BeforeAndAfterEach {

  def mock[T](implicit ev: ClassTag[T]): T =
    Mockito.mock(ev.runtimeClass.asInstanceOf[Class[T]])
}
