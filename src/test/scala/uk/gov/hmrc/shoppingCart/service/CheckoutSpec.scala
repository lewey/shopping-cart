package uk.gov.hmrc.shoppingCart.service

import org.scalatest.wordspec.AnyWordSpec
import uk.gov.hmrc.shoppingCart.models.{Apple, Fruit, Orange}

class CheckoutSpec extends AnyWordSpec {

  "totalFruitCost" should {

    "calculate the cost when no discount should be applied" in{
      val checkout = new Checkout
      val items = List(Apple, Orange)

      val result: String = checkout.totalFruitCost(items)

      println(result)
      assert(result == "£0.85")
    }

    "calculate the total cost of two apples when the apple offer is applied" in {

      val checkout = new Checkout
      val items = List(Apple, Apple)

      val result: String = checkout.totalFruitCost(items)

      println(result)
      assert(result == "£0.6")
    }

    "calculate the total cost of three apples when the apple offer is applied" in {

      val checkout = new Checkout
      val items = List(Apple, Apple, Apple)

      val result: String = checkout.totalFruitCost(items)

      println(result)
      assert(result == "£1.2")
    }

    "calculate the total cost of three oranges when the orange offer is applied" in {

      val checkout = new Checkout
      val items = List(Orange, Orange, Orange)

      val result: String = checkout.totalFruitCost(items)

      println(result)
      assert(result == "£0.5")
    }

    "calculate the total cost of one orange when the orange offer is not applied" in {

      val checkout = new Checkout
      val items = List(Orange)

      val result: String = checkout.totalFruitCost(items)

      println(result)
      assert(result == "£0.25")
    }

    "calculate the total cost of two oranges when the orange offer is not applied" in {

      val checkout = new Checkout
      val items = List(Orange, Orange)

      val result: String = checkout.totalFruitCost(items)

      println(result)
      assert(result == "£0.5")
    }

    "calculate the total cost of four Oranges when the orange offer is applied" in {

      val checkout = new Checkout
      val items = List(Orange, Orange, Orange, Orange)

      val result: String = checkout.totalFruitCost(items)

      println(result)
      assert(result == "£0.75")
    }

    "calculate the total cost of six Oranges when the orange offer is applied" in {

      val checkout = new Checkout
      val items = List(Orange, Orange, Orange, Orange, Orange, Orange)

      val result: String = checkout.totalFruitCost(items)

      println(result)
      assert(result == "£1.0")
    }

    "return zero amount for an empty list" in {
      val checkout = new Checkout

      val result: String = checkout.totalFruitCost(List.empty[Fruit])

      assert(result == "£0.0")
    }
  }

  "totalStrCost" should {
    "calculate the cost when no discount should be applied" in{
      val checkout = new Checkout
      val items = List("Apple", "Orange")

      val result: String = checkout.totalStrCost(items)

      println(result)
      assert(result == "£0.85")
    }

    "calculate the total cost of two apples when the apple offer is applied" in {

      val checkout = new Checkout
      val items = List("Apple", "Apple")

      val result: String = checkout.totalStrCost(items)

      println(result)
      assert(result == "£0.6")
    }

    "calculate the total cost of three apples when the apple offer is applied" in {

      val checkout = new Checkout
      val items = List("Apple", "Apple", "Apple")

      val result: String = checkout.totalStrCost(items)

      println(result)
      assert(result == "£1.2")
    }

    "calculate the total cost of three oranges when the orange offer is applied" in {

      val checkout = new Checkout
      val items = List("Orange", "Orange", "Orange")

      val result: String = checkout.totalStrCost(items)

      println(result)
      assert(result == "£0.5")
    }

    "calculate the total cost of one orange when the orange offer is not applied" in {

      val checkout = new Checkout
      val items = List("Orange")

      val result: String = checkout.totalStrCost(items)

      println(result)
      assert(result == "£0.25")
    }

    "calculate the total cost of two oranges when the orange offer is not applied" in {

      val checkout = new Checkout
      val items = List("Orange", "Orange")

      val result: String = checkout.totalStrCost(items)

      println(result)
      assert(result == "£0.5")
    }

    "calculate the total cost of four Oranges when the orange offer is applied" in {

      val checkout = new Checkout
      val items = List("Orange", "Orange", "Orange", "Orange")

      val result: String = checkout.totalStrCost(items)

      println(result)
      assert(result == "£0.75")
    }

    "calculate the total cost of six Oranges when the orange offer is applied" in {

      val checkout = new Checkout
      val items = List("Orange", "Orange", "Orange", "Orange", "Orange", "Orange")

      val result: String = checkout.totalStrCost(items)

      println(result)
      assert(result == "£1.0")
    }

    "return zero amount for an empty list" in {
      val checkout = new Checkout

      val result: String = checkout.totalFruitCost(List.empty[Fruit])

      assert(result == "£0.0")
    }
  }



}
