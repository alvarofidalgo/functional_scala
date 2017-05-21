package dispenser

import dispenser.MachineTypes.MachineTransition


class CoinDeposit extends Deposit[MachineTransition]{


  def select(price:Int):MachineTransition[Int] = (machine)=> {
    val actualState = state(amount=machine.amount,price=price)
    actualState match {
      case Missing => (MachineState(machine.amount),machine.amount)
      case Complete =>   (MachineState(0),0)
    }

  }


  private def state(amount:Int,price:Int):MachineStates = {
    amount compare price match {
      case -1 => Missing
      case _=>   Complete
    }
  }

}
