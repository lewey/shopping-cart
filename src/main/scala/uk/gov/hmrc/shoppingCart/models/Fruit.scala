package uk.gov.hmrc.shoppingCart.models

sealed trait Fruit {
 val cost: Double
}

case object Apple extends Fruit {
  val cost = 60
}

case object Orange extends Fruit {
  val cost = 25
}

case object Banana extends Fruit {
  val cost = 20
}