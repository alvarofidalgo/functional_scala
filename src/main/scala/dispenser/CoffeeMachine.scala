package dispenser

import dispenser.MachineTypes._
import dispenser.api.Machine




object CoffeeMachine extends Machine[MachineTransition]{

  def insert(amount:Int):MachineTransition[Int] =(machine) =>{
    val newAmount = machine.amount + amount
    (MachineState(newAmount),newAmount)
  }

  def select():MachineTransition[String] = (machine) =>{

    val (machineState,_) = CoinDeposit.select(price=40)(machine)
    val (_,message) = MessageMachine.select(price=40)(machine)
    (machineState,message)

  }

}