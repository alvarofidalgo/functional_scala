package dispenser

import org.junit.runner.RunWith
import org.scalatest.FlatSpec
import org.scalatest.ShouldMatchers
import org.scalatest.junit.JUnitRunner


@RunWith(classOf[JUnitRunner])
class CoffeeMachineTest extends FlatSpec with ShouldMatchers {


  val coffeeMachine = CoffeeMachine
  val coffeeStateZero = new MachineState(amount = 0)

  behavior of " John use coffee machine and result "



  it should " be '30 cents is missing' when 10 cents was introduced and coffee was selected " in {

    val fInsert = coffeeMachine.insert(amount=10)
    val fSelect = coffeeMachine.select()
    val (newStateMachine,_) = fInsert(coffeeStateZero)
    fSelect(newStateMachine) shouldBe (newStateMachine,"30 cents is missing")
  }


  it should " be '20 cents is missing' when 20 cents was introduced and coffee was selected " in {
    val fInsert = coffeeMachine.insert(amount=20)
    val fSelect = coffeeMachine.select()
    val (newStateMachine,_) = fInsert(coffeeStateZero)
    fSelect(newStateMachine) shouldBe  (newStateMachine,"20 cents is missing")
  }

  it should " be 'your coffee' when 40 cents was introduced and coffee was selected and return zero" in {

    val fInsert = coffeeMachine.insert(amount=40)
    val fSelect = coffeeMachine.select()
    val (newStateMachine,_) = fInsert(coffeeStateZero)
    fSelect(newStateMachine) shouldBe (MachineState(amount = 0),"your coffee")
  }



}