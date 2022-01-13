package uk.gov.hmrc.shoppingCart.models.offers
import uk.gov.hmrc.shoppingCart.config.ApplicationConfig
import uk.gov.hmrc.shoppingCart.models.{Fruit, Orange}

class OrangeOffer extends Offer{
  override def isOfferEnabled(applicationConfig: ApplicationConfig): Boolean = applicationConfig.isOrangeOfferEnabled()
  override def calculateReduction(items: List[Fruit]): Double = {
    val orangeCount: Int = items.count(_ == Orange)
    if(orangeCount < 3) 0 else (orangeCount / 3) * Orange.cost
  }
}
