package dispenser.components

import dispenser.MachineState
import org.scalatest.{FlatSpec, ShouldMatchers}


class CoinDepositTest extends FlatSpec with ShouldMatchers{


  behavior of " Coin deposit have money and result "

  val coinDeposit = CoinDeposit


  it should " be return 10 when 30 cents is missing " in {
    val fSelect = coinDeposit.coins(price = 40)
    fSelect(MachineState(amount = 10)) shouldBe MachineState(amount = 10)

  }


  it should " be return 20 when 20 cents is missing " in {

    val fSelect =  coinDeposit.coins(price = 40)
    fSelect(MachineState(amount = 20)) shouldBe MachineState(amount = 20)
  }


  it should " be return 0 when amount is eual to price " in {
    val fSelect =coinDeposit.coins(price = 40)
    fSelect(MachineState(amount = 40)) shouldBe MachineState(amount = 0)
  }

}
