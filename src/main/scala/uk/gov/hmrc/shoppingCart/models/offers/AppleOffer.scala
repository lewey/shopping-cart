package uk.gov.hmrc.shoppingCart.models.offers

import uk.gov.hmrc.shoppingCart.config.ApplicationConfig
import uk.gov.hmrc.shoppingCart.models.{Apple, Fruit}

class AppleOffer extends Offer {
  override def calculateReduction(items: List[Fruit]): Double = calculateOneFreeReduction(items, Apple)
  override def isOfferEnabled(applicationConfig: ApplicationConfig): Boolean = applicationConfig.isAppleOfferEnabled()
}
