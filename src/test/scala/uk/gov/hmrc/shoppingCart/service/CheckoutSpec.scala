package uk.gov.hmrc.shoppingCart.service

import org.scalatest.wordspec.AnyWordSpec
import uk.gov.hmrc.shoppingCart.models.{Apple, Banana, Fruit, Orange}

class CheckoutSpec extends AnyWordSpec {

  "totalFruitCost" should {
    "calculate the cost when no discount should be applied" in {
      val checkout = new Checkout
      val items = List(Apple, Orange)

      val result: String = checkout.totalFruitCost(items)
      assert(result == "£0.85")
    }

    "apply an offer of buy one get one free" when {
      "calculating the cost of two apples" in {
        val checkout = new Checkout
        val items = List(Apple, Apple)

        val result: String = checkout.totalFruitCost(items)
        assert(result == "£0.6")
      }

      "calculating the cost of three apples" in {
        val checkout = new Checkout
        val items = List(Apple, Apple, Apple)

        val result: String = checkout.totalFruitCost(items)
        assert(result == "£1.2")
      }
    }

    "calculate the total cost of one orange" in {
      val checkout = new Checkout
      val items = List(Orange)

      val result: String = checkout.totalFruitCost(items)
      assert(result == "£0.25")
    }

    "calculate the total cost of two oranges" in {
      val checkout = new Checkout
      val items = List(Orange, Orange)

      val result: String = checkout.totalFruitCost(items)
      assert(result == "£0.5")
    }

    "apply an offer of three for two" when {
      "calculating the total cost of three oranges" in {
        val checkout = new Checkout
        val items = List(Orange, Orange, Orange)

        val result: String = checkout.totalFruitCost(items)
        assert(result == "£0.5")

      }

      "calculating the total cost of four Oranges" in {
        val checkout = new Checkout
        val items = List(Orange, Orange, Orange, Orange)

        val result: String = checkout.totalFruitCost(items)
        assert(result == "£0.75")
      }

      "calculating the total cost of six Oranges" in {
        val checkout = new Checkout
        val items = List(Orange, Orange, Orange, Orange, Orange, Orange)

        val result: String = checkout.totalFruitCost(items)
        assert(result == "£1.0")
      }
    }

    "calculate the total cost of one banana" in {
      val checkout = new Checkout
      val items = List(Banana)

      val result: String = checkout.totalFruitCost(items)
      assert(result == "£0.2")
    }

    "calculate the total cost of two banana" should {
      "apply the discount of buy one get one free" in {
        val checkout = new Checkout
        val items = List(Banana, Banana)

        val result: String = checkout.totalFruitCost(items)
        assert(result == "£0.2")
      }
    }

    "apply an offer of cheapest free" when {
      "one apple and one banana are bought together" in {
        val checkout = new Checkout
        val items = List(Banana, Apple)

        val result: String = checkout.totalFruitCost(items)
        assert(result == "£0.6")
      }

      "two banana and one apple is bough together" in {
        val checkout = new Checkout
        val items = List(Banana, Banana, Apple)

        val result: String = checkout.totalFruitCost(items)
        assert(result == "£0.8")
      }

      "when one banana and two apples are bought together" in {
        val checkout = new Checkout
        val items = List(Banana, Apple, Apple)

        val result: String = checkout.totalFruitCost(items)
        assert(result == "£1.2")
      }
    }

    "return zero amount for an empty list" in {
      val checkout = new Checkout

      val result: String = checkout.totalFruitCost(List.empty[Fruit])
      assert(result == "£0.0")
    }
  }

  "totalStrCost" should {
    "calculate the cost when no discount should be applied" in {
      val checkout = new Checkout
      val items = List("Apple", "Orange")

      val result: String = checkout.totalStrCost(items)
      assert(result == "£0.85")
    }

    "apply an offer of buy one get one free" when {
      "calculating the cost of two apples" in {
        val checkout = new Checkout
        val items = List("Apple", "Apple")

        val result: String = checkout.totalStrCost(items)
        assert(result == "£0.6")
      }

      "calculating the cost of three apples" in {
        val checkout = new Checkout
        val items = List("Apple", "Apple", "Apple")

        val result: String = checkout.totalStrCost(items)
        assert(result == "£1.2")
      }
    }

    "calculate the total cost of one orange" in {
      val checkout = new Checkout
      val items = List("Orange")

      val result: String = checkout.totalStrCost(items)
      assert(result == "£0.25")
    }

    "calculate the total cost of two oranges" in {
      val checkout = new Checkout
      val items = List("Orange", "Orange")

      val result: String = checkout.totalStrCost(items)
      assert(result == "£0.5")
    }

    "apply an offer of three for two" when {
      "calculating the total cost of three oranges" in {
        val checkout = new Checkout
        val items = List("Orange", "Orange", "Orange")

        val result: String = checkout.totalStrCost(items)
        assert(result == "£0.5")

      }

      "calculating the total cost of four Oranges" in {
        val checkout = new Checkout
        val items = List("Orange", "Orange", "Orange", "Orange")

        val result: String = checkout.totalStrCost(items)
        assert(result == "£0.75")
      }

      "calculating the total cost of six Oranges" in {
        val checkout = new Checkout
        val items = List("Orange", "Orange", "Orange", "Orange", "Orange", "Orange")

        val result: String = checkout.totalStrCost(items)
        assert(result == "£1.0")
      }
    }

    "calculate the total cost of one banana" in {
      val checkout = new Checkout
      val items = List("Banana")

      val result: String = checkout.totalStrCost(items)
      assert(result == "£0.2")
    }

    "calculate the total cost of two banana" should {
      "apply the discount of buy one get one free" in {
        val checkout = new Checkout
        val items = List("Banana", "Banana")

        val result: String = checkout.totalStrCost(items)
        assert(result == "£0.2")
      }
    }

    "apply an offer of cheapest free" when {
      "one apple and one banana are bought together" in {
        val checkout = new Checkout
        val items = List("Banana", "Apple")

        val result: String = checkout.totalStrCost(items)
        assert(result == "£0.6")
      }

      "two banana and one apple is bough together" in {
        val checkout = new Checkout
        val items = List("Banana", "Banana", "Apple")

        val result: String = checkout.totalStrCost(items)
        assert(result == "£0.8")
      }

      "when one banana and two apples are bought together" in {
        val checkout = new Checkout
        val items = List("Banana", "Apple", "Apple")

        val result: String = checkout.totalStrCost(items)
        assert(result == "£1.2")
      }
    }

    "return zero amount for an empty list" in {
      val checkout = new Checkout

      val result: String = checkout.totalStrCost(List.empty[String])
      assert(result == "£0.0")
    }
  }
}
