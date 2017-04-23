package dispenser


case class Machine(state:TypeState,amount:Int) {



   def insert(money:Int):Machine = {
     val value = amount + money
     Machine(state = new MachineState().next(value),amount = value)
   }


}
