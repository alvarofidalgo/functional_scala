package dispenser

import org.junit.runner.RunWith
import org.scalatest.FlatSpec
import org.scalatest.ShouldMatchers
import org.scalatest.junit.JUnitRunner


@RunWith(classOf[JUnitRunner])
class CoffeeMachineTest extends FlatSpec with ShouldMatchers {

  behavior of " John use coffee machine and result "



  it should " be '30 cents is missing' when 10 cents was introduced and coffee was selected " in {
    val coffeeMachine = new CoffeeMachine[Int](amount = 0,price = 40)
    val newStateMachine = coffeeMachine.insert(amount=10)
    newStateMachine.select() shouldBe "30 cents is missing"
  }


  it should " be '20 cents is missing' when 20 cents was introduced and coffee was selected " in {
    val coffeeMachine = new CoffeeMachine[Double](amount = 0,price = 40)
    val newStateMachine = coffeeMachine.insert(amount=20)
    newStateMachine.select() shouldBe "20 cents is missing"
  }

  it should " be 'your coffee' when 40 cents was introduced and coffee was selected " in {
    val coffeeMachine = new CoffeeMachine[String](amount = "0",price = "40")
    val newStateMachine = coffeeMachine.insert(amount="40")
    newStateMachine.select() shouldBe "your coffee"
  }



}