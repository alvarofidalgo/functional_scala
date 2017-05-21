package dispenser


import types.StateTypes.State



case class CoffeeMachine(amount:Seq[Int] = Seq.empty[Int]) {

  def insert(amount:Int):CoffeeMachine = {
    new CoffeeMachine(this.amount ++ Seq(amount))
  }

  def select():State[CoffeeMachine,String] = (machine)=>{

    state(amount = machine.amount.foldLeft(0)((a,b)=>a+b),price = 40) match {
      case Missing=>
        val amountMissing = 40 - machine.amount.foldLeft(0)((a,b)=>a+b)
        val message =s"$amountMissing cents is missing"
        (message,machine)
      case Complete=>  ("your coffee",machine)
    }
  }

  private def state(amount:Int,price:Int):MachineStates = {
    amount compare 40 match {
      case -1 => Missing
      case _=>   Complete
    }
  }

}