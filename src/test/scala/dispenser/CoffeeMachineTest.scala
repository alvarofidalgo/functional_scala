package dispenser

import org.junit.runner.RunWith
import org.scalatest.FlatSpec
import org.scalatest.ShouldMatchers
import org.scalatest.junit.JUnitRunner


@RunWith(classOf[JUnitRunner])
class CoffeeMachineTest extends FlatSpec with ShouldMatchers {


  val coffeeMachine = new CoffeeMachine()

  behavior of " John use coffee machine and result "



  it should " be '30 cents is missing' when 10 cents was introduced and coffee was selected " in {

    val newStateMachine = coffeeMachine.insert(amount=10)
    newStateMachine.select()(newStateMachine) shouldBe ("30 cents is missing",newStateMachine)
  }


  it should " be '20 cents is missing' when 20 cents was introduced and coffee was selected " in {

    val newStateMachine = coffeeMachine.insert(amount=20)
    newStateMachine.select()(newStateMachine) shouldBe ("20 cents is missing",newStateMachine)
  }

  it should " be 'your coffee' when 40 cents was introduced and coffee was selected " in {

    val newStateMachine = coffeeMachine.insert(amount=40)
    newStateMachine.select()(newStateMachine) shouldBe ("your coffee",newStateMachine)
  }



}