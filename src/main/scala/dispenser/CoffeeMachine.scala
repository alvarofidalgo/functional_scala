package dispenser

import dispenser.api.Machine
import dispenser.components.{CoinDeposit, MessageMachine}
import dispenser.types.MachineTypes.MachineTransition




object CoffeeMachine extends Machine[MachineTransition]{

  def insert(amount:Int):MachineTransition[Int] =(machine) =>{
    val newAmount = machine.amount + amount
    (MachineState(newAmount),newAmount)
  }

  def select():MachineTransition[String] = (machine) =>{

    val machineState = CoinDeposit.coins(price=40)(machine)
    val message= MessageMachine.messageShow(price=40)(machine)
    (machineState,message)

  }

}