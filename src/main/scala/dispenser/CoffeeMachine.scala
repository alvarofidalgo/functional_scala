package dispenser

import dispenser.MachineTypes._


case class MachineState(amount:Int)

object CoffeeMachine extends Machine[MachineTransition]{

  def insert(amount:Int):MachineTransition[Int] =(machine) =>{
    val newAmount = machine.amount + amount
    (MachineState(newAmount),newAmount)
  }

  def select():MachineTransition[String] = (machine) =>{

    state(amount = machine.amount ,price = 40) match {
      case Missing=>
        val amountMissing = 40 - machine.amount
        val message = s"$amountMissing cents is missing"
        val machineState = MachineState(machine.amount)
        (machineState,message)
      case Complete=>
        val message = "your coffee"
        (MachineState(amount = 0),message)
    }
  }

  private def state(amount:Int,price:Int):MachineStates = {
    amount compare 40 match {
      case -1 => Missing
      case _=>   Complete
    }
  }

}