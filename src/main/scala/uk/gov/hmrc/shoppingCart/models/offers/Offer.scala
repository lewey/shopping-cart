package uk.gov.hmrc.shoppingCart.models.offers

import uk.gov.hmrc.shoppingCart.config.ApplicationConfig
import uk.gov.hmrc.shoppingCart.models.Fruit

trait Offer {

  protected def calculateOneFreeReduction(items: List[Fruit], fruitType: Fruit): Double = {
    val fruitCount: Int = items.count(_ == fruitType)

    (fruitCount < 2, fruitCount % 2 == 0) match {
      case (true, _) => 0
      case (false, true) => fruitCount / 2 * fruitType.cost
      case (_, _) => (fruitCount - 1) / 2 * fruitType.cost
    }
  }

  def isOfferEnabled(applicationConfig: ApplicationConfig): Boolean

  def calculateReduction(items: List[Fruit]): Double
}
