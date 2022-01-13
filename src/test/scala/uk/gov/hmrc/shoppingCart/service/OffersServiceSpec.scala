package uk.gov.hmrc.shoppingCart.service

import org.mockito.Mockito
import org.mockito.Mockito.when
import uk.gov.hmrc.shoppingCart.BaseSpec
import uk.gov.hmrc.shoppingCart.config.ApplicationConfig
import uk.gov.hmrc.shoppingCart.models.{Apple, Banana, Fruit, Orange}

class OffersServiceSpec extends BaseSpec {
  val mockApplicationConfig: ApplicationConfig = mock[ApplicationConfig]

  override def beforeEach() {
    Mockito.reset(mockApplicationConfig)
  }

  def setToggles(state: Boolean): Unit ={
    when(mockApplicationConfig.isBananaOfferEnabled()).thenReturn(state)
    when(mockApplicationConfig.isOrangeOfferEnabled()).thenReturn(state)
    when(mockApplicationConfig.isAppleOfferEnabled()).thenReturn(state)
    when(mockApplicationConfig.isAppleAndBananaOfferEnabled()).thenReturn(state)
  }

 "calculateTotalDiscount" when {
   "all offers are toggled on" should {
     "return 0 for an empty list" in {
       setToggles(true)
       val offersService = new OffersService(mockApplicationConfig)
       val result: Double = offersService.calculateTotalDiscount(List.empty[Fruit])
       assert(result == 0)
     }

     "not apply an offer" when {
       "there is only one Apple" in {
         setToggles(true)
         val offersService = new OffersService(mockApplicationConfig)
         val items = List(Apple)

         val result: Double = offersService.calculateTotalDiscount(items)
         assert(result == 0)
       }
     }

     "apply an offer of buy one get one free" when {
       "calculating the cost of two apples" in {
         setToggles(true)
         val offersService = new OffersService(mockApplicationConfig)
         val items = List(Apple, Apple)

         val result: Double = offersService.calculateTotalDiscount(items)
         assert(result == 60)
       }

       "calculating the cost of three apples" in {
         setToggles(true)
         val offersService = new OffersService(mockApplicationConfig)
         val items = List(Apple, Apple, Apple)

         val result: Double = offersService.calculateTotalDiscount(items)
         assert(result == 60)
       }
     }


     "apply an offer of three for two" when {
       "calculating the total cost of three oranges" in {
         setToggles(true)
         val offersService = new OffersService(mockApplicationConfig)
         val items = List(Orange, Orange, Orange)

         val result: Double = offersService.calculateTotalDiscount(items)
         assert(result == 25)
       }

       "calculating the total cost of four Oranges" in {
         setToggles(true)
         val offersService = new OffersService(mockApplicationConfig)
         val items = List(Orange, Orange, Orange, Orange)

         val result: Double = offersService.calculateTotalDiscount(items)
         assert(result == 25)
       }

       "calculating the total cost of six Oranges" in {
         setToggles(true)
         val offersService = new OffersService(mockApplicationConfig)
         val items = List(Orange, Orange, Orange, Orange, Orange, Orange)

         val result: Double = offersService.calculateTotalDiscount(items)
         assert(result == 50)
       }
     }

     "apply an offer of buy one get one free" when {
       "calculating the cost of two bananas" in {
         setToggles(true)
         val offersService = new OffersService(mockApplicationConfig)
         val items = List(Banana, Banana)

         val result: Double = offersService.calculateTotalDiscount(items)
         assert(result == 20)
       }
     }

     "apply an offer of cheapest free" when {
       "one apple and one banana are bought together" in {
         setToggles(true)
         val offersService = new OffersService(mockApplicationConfig)
         val items = List(Banana, Apple)

         val result: Double = offersService.calculateTotalDiscount(items)
         assert(result == 20)
       }

       "two banana and one apple is bough together" in {
         setToggles(true)
         val offersService = new OffersService(mockApplicationConfig)
         val items = List(Banana, Banana, Apple)

         val result: Double = offersService.calculateTotalDiscount(items)
         assert(result == 20)
       }

       "when one banana and two apples are bought together" in {
         setToggles(true)
         val offersService = new OffersService(mockApplicationConfig)
         val items = List(Banana, Apple, Apple)

         val result: Double = offersService.calculateTotalDiscount(items)
         assert(result == 20)
       }
     }
   }

   "all offers are toggled off" should {
     "return zero" in {
       setToggles(false)
       val offersService = new OffersService(mockApplicationConfig)
       val items = List(Banana, Apple, Apple, Orange)

       val result: Double = offersService.calculateTotalDiscount(items)
       assert(result == 0)
     }
   }

   "calculate single offers" when {
     "applesAndBananas offer is toggled off" in {
       setToggles(true)
       when(mockApplicationConfig.isAppleAndBananaOfferEnabled()).thenReturn(false)

       val offersService = new OffersService(mockApplicationConfig)
       val items = List(Banana, Apple, Apple, Orange)

       val result: Double = offersService.calculateTotalDiscount(items)
       assert(result == 60)
     }
   }
 }
}
