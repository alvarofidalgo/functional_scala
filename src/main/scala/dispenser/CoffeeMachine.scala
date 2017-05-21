package dispenser


class CoffeeMachine(amount:Int) {

  def insert(amount:Int):CoffeeMachine = {
    new CoffeeMachine(this.amount + amount)
  }

  def select():String = {

    state(amount = this.amount ,price = 40) match {
      case Missing=>
        val amountMissing = 40 - amount
        s"$amountMissing cents is missing"
      case Complete=>   "your coffee"
    }
  }

  private def state(amount:Int,price:Int):MachineStates = {
    amount compare 40 match {
      case -1 => Missing
      case _=>   Complete
    }
  }

}