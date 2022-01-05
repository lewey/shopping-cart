package uk.gov.hmrc.shoppingCart.service

import uk.gov.hmrc.shoppingCart.models.{Apple, Fruit, Orange}

class Checkout {

  def calculateAppleOfferReduction(items: List[Fruit]): Double = {
    val appleCount: Int = items.count(_ == Apple)

    (appleCount < 2, appleCount % 2 == 0) match {
      case (true, _) => 0
      case (false, true) => appleCount / 2 * Apple.cost
      case (_, _) => (appleCount - 1) / 2 * Apple.cost
    }
  }

  def calculateOrangeOfferReduction(items: List[Fruit]): Double = {
    val orangeCount: Int = items.count(_ == Orange)
    if(orangeCount < 3) 0 else (orangeCount / 3) * Orange.cost
  }

  def totalFruitCost(items: List[Fruit]): String = {
    convertToCurrency(calculateTotalCost(items))
  }

  def totalStrCost(items: List[String]): String = {
    val allFruitItems: List[Fruit] = items.map {
              case "Apple" => Apple
              case "Orange" => Orange
    }

    val totalCost: Double = calculateTotalCost(allFruitItems)

    convertToCurrency(totalCost)
  }

  private def calculateTotalCost(items: List[Fruit]): Double = {
    val costWithoutReduction: Double = items.map(_.cost).sum
    val totalReduction = calculateAppleOfferReduction(items) + calculateOrangeOfferReduction(items)
    costWithoutReduction - totalReduction
  }



  private def convertToCurrency(amount: Double): String = {
    val currencyAmount = amount / 100
    s"£$currencyAmount"
  }
}
