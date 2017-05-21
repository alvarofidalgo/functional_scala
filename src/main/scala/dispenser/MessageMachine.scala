package dispenser

import dispenser.MachineTypes.MachineTransition
import dispenser.api.Selector


object MessageMachine extends Selector[MachineTransition]{

  def select(price:Int):MachineTransition[String] = (machine)=>{
    StateTransition.state(amount = machine.amount ,price = price) match {
      case Missing=>
        val amountMissing = price - machine.amount
        (machine,s"$amountMissing cents is missing")
      case Complete=>
        val message = "your coffee"
        (machine,message)
    }
  }

}
