package uk.gov.hmrc.shoppingCart.service

import org.mockito.Mockito.when
import uk.gov.hmrc.shoppingCart.BaseSpec
import uk.gov.hmrc.shoppingCart.models.{Apple, Banana, Fruit, Orange}

class CheckoutSpec extends BaseSpec {

  val mockOffersService = mock[OffersService]

  "totalFruitCost" should {
    "calculate the cost when no discount should be applied" in {
      val checkout = new Checkout(mockOffersService)
      val items = List(Apple, Orange)

      when(mockOffersService.calculateTotalDiscount(items)).thenReturn(0)
      val result: String = checkout.totalFruitCost(items)
      assert(result == "£0.85")
    }

    "calculating the cost of two apples" in {
      val checkout = new Checkout(mockOffersService)
      val items = List(Apple, Apple)

      when(mockOffersService.calculateTotalDiscount(items)).thenReturn(60.0)
      val result: String = checkout.totalFruitCost(items)
      assert(result == "£0.6")
    }

    "calculate the total cost of one orange" in {
      val checkout = new Checkout(mockOffersService)
      val items = List(Orange)

      when(mockOffersService.calculateTotalDiscount(items)).thenReturn(0)
      val result: String = checkout.totalFruitCost(items)
      assert(result == "£0.25")
    }

    "calculate the total cost of two oranges" in {
      val checkout = new Checkout(mockOffersService)
      val items = List(Orange, Orange)
      when(mockOffersService.calculateTotalDiscount(items)).thenReturn(0)

      val result: String = checkout.totalFruitCost(items)
      assert(result == "£0.5")
    }

    "calculating the total cost of three oranges" in {
      val checkout = new Checkout(mockOffersService)
      val items = List(Orange, Orange, Orange)
      when(mockOffersService.calculateTotalDiscount(items)).thenReturn(25)

      val result: String = checkout.totalFruitCost(items)
      assert(result == "£0.5")
    }

    "calculate the total cost of one banana" in {
      val checkout = new Checkout(mockOffersService)
      val items = List(Banana)
      when(mockOffersService.calculateTotalDiscount(items)).thenReturn(0)

      val result: String = checkout.totalFruitCost(items)
      assert(result == "£0.2")
    }

    "calculate the total cost of two banana" in {
      val checkout = new Checkout(mockOffersService)
      val items = List(Banana, Banana)
      when(mockOffersService.calculateTotalDiscount(items)).thenReturn(20)

      val result: String = checkout.totalFruitCost(items)
      assert(result == "£0.2")
    }

    "calculate the total cost" when {
      "one apple and one banana are bought together" in {
        val checkout = new Checkout(mockOffersService)
        val items = List(Banana, Apple)
        when(mockOffersService.calculateTotalDiscount(items)).thenReturn(20)

        val result: String = checkout.totalFruitCost(items)
        assert(result == "£0.6")
      }

      "two banana and one apple is bough together" in {
        val checkout = new Checkout(mockOffersService)
        val items = List(Banana, Banana, Apple)
        when(mockOffersService.calculateTotalDiscount(items)).thenReturn(20)

        val result: String = checkout.totalFruitCost(items)
        assert(result == "£0.8")
      }

      "when one banana and two apples are bought together" in {
        val checkout = new Checkout(mockOffersService)
        val items = List(Banana, Apple, Apple)
        when(mockOffersService.calculateTotalDiscount(items)).thenReturn(20)

        val result: String = checkout.totalFruitCost(items)
        assert(result == "£1.2")
      }
    }

    "return zero amount for an empty list" in {
      val checkout = new Checkout(mockOffersService)

      val result: String = checkout.totalFruitCost(List.empty[Fruit])
      assert(result == "£0.0")
    }
  }
}
