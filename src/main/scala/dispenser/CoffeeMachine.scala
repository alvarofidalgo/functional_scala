package dispenser

import dispenser.MachineTypes._




object CoffeeMachine extends Machine[MachineTransition]{

  def insert(amount:Int):MachineTransition[Int] =(machine) =>{
    val newAmount = machine.amount + amount
    (MachineState(newAmount),newAmount)
  }

  def select():MachineTransition[String] = (machine) =>{

    val (machineState,_) = CoinDeposit.select(price=40)(machine)

    StateTransition.state(amount = machine.amount ,price = 40) match {
      case Missing=>
        val amountMissing = 40 - machine.amount
        val message = s"$amountMissing cents is missing"
        (machineState,message)
      case Complete=>
        val message = "your coffee"
        (machineState,message)
    }
  }

}