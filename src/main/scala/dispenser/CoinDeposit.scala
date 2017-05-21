package dispenser

import dispenser.MachineTypes.MachineTransition
import dispenser.api.Selector


object CoinDeposit extends Selector[MachineTransition]{


  def select(price:Int):MachineTransition[Int] = (machine)=> {
    val actualState = StateTransition.state(amount=machine.amount,price=price)
    actualState match {
      case Missing => (MachineState(machine.amount),machine.amount)
      case Complete =>   (MachineState(0),0)
    }

  }

}
