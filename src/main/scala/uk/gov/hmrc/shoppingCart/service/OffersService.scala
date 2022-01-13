package uk.gov.hmrc.shoppingCart.service

import uk.gov.hmrc.shoppingCart.config.ApplicationConfig
import uk.gov.hmrc.shoppingCart.models.offers._
import uk.gov.hmrc.shoppingCart.models.{Apple, Banana, Fruit}

class OffersService(applicationConfig: ApplicationConfig) {
  val separateOffers: List[Offer] = List(new AppleOffer, new OrangeOffer, new BananaOffer)
  val appleAndBananaComboOffers: List[Offer] = List(new AppleAndBananaOffer, new OrangeOffer)

  def calculateTotalDiscount(items: List[Fruit]): Double = {

    def cartItemCheck(fruit: Fruit): Boolean = items.contains(fruit)
    def applesAndBanans: Boolean = cartItemCheck(Apple) && cartItemCheck(Banana)

    val currentOffers: List[Offer] = if(applesAndBanans && applicationConfig.isAppleAndBananaOfferEnabled()){
      appleAndBananaComboOffers
    }else{
      separateOffers
    }

    currentOffers.filter(_.isOfferEnabled(applicationConfig)).map(_.calculateReduction(items)).sum
  }
}
