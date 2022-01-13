package uk.gov.hmrc.shoppingCart.service

import uk.gov.hmrc.shoppingCart.models.{Apple, Banana, Fruit, Orange}

class Checkout(val offersService: OffersService) {

  def totalFruitCost(items: List[Fruit]): String = {
    convertToCurrency(calculateTotalCost(items))
  }

  def totalStrCost(items: List[String]): String = {
    val allFruitItems: List[Fruit] = items.map {
      case "Apple" => Apple
      case "Orange" => Orange
      case "Banana" => Banana
    }

    convertToCurrency(calculateTotalCost(allFruitItems))
  }

  private def calculateTotalCost(items: List[Fruit]): Double = {
    val costWithoutReduction: Double = items.map(_.cost).sum
    val totalReduction = totalReductions(items)
    costWithoutReduction - totalReduction
  }

  private def totalReductions(items: List[Fruit]): Double = offersService.calculateTotalDiscount(items)

  private def convertToCurrency(amount: Double): String = {
    val currencyAmount = amount / 100
    s"£$currencyAmount"
  }
}
