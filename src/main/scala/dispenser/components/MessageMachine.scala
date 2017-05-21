package dispenser.components

import dispenser.StateTransition
import dispenser.api.Message
import dispenser.types.MachineTypes.StateNoTransition
import dispenser.types.Complete
import dispenser.types.Missing


object MessageMachine extends Message[StateNoTransition]{

  def messageShow(price:Int):StateNoTransition[String] = (machine)=>{
    StateTransition.state(amount = machine.amount ,price = price) match {
      case Missing=>
        val amountMissing = price - machine.amount
        s"$amountMissing cents is missing"
      case Complete=>
        val message = "your coffee"
        message
    }
  }

}
