package uk.gov.hmrc.shoppingCart.models.offers
import uk.gov.hmrc.shoppingCart.config.ApplicationConfig
import uk.gov.hmrc.shoppingCart.models.{Apple, Banana, Fruit}

class AppleAndBananaOffer extends Offer {
  override def isOfferEnabled(applicationConfig: ApplicationConfig): Boolean = applicationConfig.isAppleAndBananaOfferEnabled()
  override def calculateReduction(items: List[Fruit]): Double = {
    val bananaCount: Int = items.count(_ == Banana)
    val appleCount: Int = items.count(_ == Apple)

    if(bananaCount <= appleCount) bananaCount * Banana.cost else appleCount * Banana.cost
  }
}
