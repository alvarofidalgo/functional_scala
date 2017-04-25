package dispenser

import types.StateTypes._

case class Machine(amount:Int) {

   def insert(money:Int):State[Machine,(TypeState,Int)] = {
     val value = amount + money
     (machine) =>

       ((new MachineState().next(value),value),Machine(value))

   }
}
