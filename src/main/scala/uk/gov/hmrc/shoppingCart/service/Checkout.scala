package uk.gov.hmrc.shoppingCart.service

import uk.gov.hmrc.shoppingCart.models.{Apple, Fruit, Orange}

class Checkout {

  def totalFruitCost(items: List[Fruit]): String = {
    val totalCost: Double = calculateTotalCost(items)
    convertToCurrency(totalCost)
  }

  def totalStrCost(items: List[String]): String = {
    val allFruitItems: List[Fruit] = items.map {
              case "Apple" => Apple
              case "Orange" => Orange
    }

    val totalCost: Double = calculateTotalCost(allFruitItems)
    convertToCurrency(totalCost)
  }

  private def calculateTotalCost(items: List[Fruit]): Double = items.map(_.cost).sum

  private def convertToCurrency(amount: Double): String = {
    val currencyAmount = amount / 100
    s"£$currencyAmount"
  }
}
