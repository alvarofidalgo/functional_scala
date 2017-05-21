package dispenser.components

import dispenser.api.Deposit
import dispenser.types.MachineTypes.StateNoTransition
import dispenser.types.{Complete, Missing}
import dispenser.{MachineState, StateTransition}


object CoinDeposit extends Deposit[StateNoTransition]{


  def coins(price:Int):StateNoTransition[MachineState] = (machine)=> {
    val actualState = StateTransition.state(amount=machine.amount,price=price)
    actualState match {
      case Missing =>  MachineState(machine.amount)
      case Complete => MachineState(0)
    }

  }

}
