package dispenser

import dispenser.MachineTypes._

class CoffeeMachine(amount:Int) extends Machine[MachineTransition]{

  def insert(amount:Int):CoffeeMachine = {
    new CoffeeMachine(this.amount + amount)
  }

  def select():MachineTransition[String] = (machine) =>{

    state(amount = this.amount ,price = 40) match {
      case Missing=>
        val amountMissing = 40 - amount
        val message = s"$amountMissing cents is missing"
        (machine,message)
      case Complete=>
        val message = "your coffee"
        (machine,message)
    }
  }

  private def state(amount:Int,price:Int):MachineStates = {
    amount compare 40 match {
      case -1 => Missing
      case _=>   Complete
    }
  }

}