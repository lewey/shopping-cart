package uk.gov.hmrc.shoppingCart.models.offers
import uk.gov.hmrc.shoppingCart.config.ApplicationConfig
import uk.gov.hmrc.shoppingCart.models.{Apple, Banana, Fruit}

class BananaOffer extends Offer {
  override def isOfferEnabled(applicationConfig: ApplicationConfig): Boolean = applicationConfig.isBananaOfferEnabled()
  override def calculateReduction(items: List[Fruit]): Double = calculateOneFreeReduction(items, Banana)
}
