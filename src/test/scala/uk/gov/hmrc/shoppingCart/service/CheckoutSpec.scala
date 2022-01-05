package uk.gov.hmrc.shoppingCart.service

import org.scalatest.wordspec.AnyWordSpec
import uk.gov.hmrc.shoppingCart.models.{Apple, Fruit, Orange}

class CheckoutSpec extends AnyWordSpec {

  "totalFruitCost" should {
    "calculate the total cost of items from a list" in {

      val checkout = new Checkout
      val items = List(Apple, Apple, Orange, Apple)

      val result: String = checkout.totalFruitCost(items)

      assert(result == "£2.05")
    }

    "return zero amount for an empty list" in {
      val checkout = new Checkout

      val result: String = checkout.totalFruitCost(List.empty[Fruit])

      assert(result == "£0.0")
    }
  }

  "totalStrCost" should {
    "calculate the total cost of items from a list" in {

      val checkout = new Checkout
      val items = List("Apple", "Apple", "Orange", "Apple")

      val result: String = checkout.totalStrCost(items)

      assert(result == "£2.05")
    }

    "return zero amount for an empty list" in {
      val checkout = new Checkout

      val result: String = checkout.totalStrCost(List.empty[String])

      assert(result == "£0.0")
    }
  }



}
