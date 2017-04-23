package dispenser

import org.junit.runner.RunWith
import org.scalatest.FlatSpec
import org.scalatest.ShouldMatchers
import org.scalatest.junit.JUnitRunner



@RunWith(classOf[JUnitRunner])
class MachineStateTest extends FlatSpec with ShouldMatchers{

  behavior of "State of machine"

  it should " be PAYMENT if amount  was  20 " in {

     val machineState = new MachineState()
      machineState.next(amount=20) shouldBe "PAYMENT"
  }


  it should " be SELECTED if amount was 40 " in {

    val machineState = new MachineState()
    machineState.next(amount=40) shouldBe "SELECTED"

  }


  it should " be PAYMENT if amount was 39 " in {

    val machineState = new MachineState()
    machineState.next(amount=39) shouldBe "PAYMENT"

  }

  it should " be SELECTED if amount was 41 " in {

    val machineState = new MachineState()
    machineState.next(amount=41) shouldBe "SELECTED"

  }

}
