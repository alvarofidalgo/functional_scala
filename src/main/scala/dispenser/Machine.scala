package dispenser


case class Machine(state:String,amount:Int) {



   def insert(money:Int):Machine = {
     val value = amount + money
     val newState =
     if (value < 40)
      "PAYMENT"
     else
       "SELECTION"
     Machine(state = newState,amount = value)
   }


}
