package uk.gov.hmrc.shoppingCart.service

import uk.gov.hmrc.shoppingCart.models.{Apple, Banana, Fruit, Orange}

class Checkout {

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

  private def calculateOneFreeReduction(items: List[Fruit], fruitType: Fruit): Double = {
    val fruitCount: Int = items.count(_ == fruitType)

    (fruitCount < 2, fruitCount % 2 == 0) match {
      case(true, _) => 0
      case(false, true) => fruitCount / 2 * fruitType.cost
      case(_, _) => (fruitCount - 1) / 2 * fruitType.cost
    }
  }

  private def calculateOrangeOfferReduction(items: List[Fruit]): Double = {
    val orangeCount: Int = items.count(_ == Orange)
    if(orangeCount < 3) 0 else (orangeCount / 3) * Orange.cost
  }

  private def calculateBananaAndAppleOfferReduction(items: List[Fruit]): Double = {
    val bananaCount: Int = items.count(_ == Banana)
    val appleCount: Int = items.count(_ == Apple)

    if(bananaCount <= appleCount) bananaCount * Banana.cost else appleCount * Banana.cost
  }

  private def calculateTotalCost(items: List[Fruit]): Double = {
    val costWithoutReduction: Double = items.map(_.cost).sum
    val totalReduction = totalReductions(items)
    costWithoutReduction - totalReduction
  }

  private def totalReductions(items: List[Fruit]): Double = {

    def cartItemCheck(fruit: Fruit): Boolean = items.contains(fruit)

    if(cartItemCheck(Apple) && cartItemCheck(Banana)){
      calculateOrangeOfferReduction(items) + calculateBananaAndAppleOfferReduction(items)
    }else{
      calculateOrangeOfferReduction(items) + calculateOneFreeReduction(items, Banana) + calculateOneFreeReduction(items, Apple)
    }
  }

  private def convertToCurrency(amount: Double): String = {
    val currencyAmount = amount / 100
    s"£$currencyAmount"
  }
}
