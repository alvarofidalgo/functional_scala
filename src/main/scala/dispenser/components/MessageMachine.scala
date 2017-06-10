package dispenser.components

import dispenser.StateTransition
import dispenser.api.Message
import dispenser.types.MachineTypes.StateNoTransition
import dispenser.types.Complete
import dispenser.types.Missing

import dispenser.types.StateNoTransitionOperations._


object MessageMachine extends Message[StateNoTransition]{

  def messageShow(price:Int):StateNoTransition[String] = (machine)=>{
    StateTransition.state(amount = machine.amount ,price = price) match {
      case Missing=>
        val amountMissing = price - machine.amount
        s"$amountMissing cents is missing"
      case Complete=>
        val message = "your coffee"
        message
    }
  }

  override def flatMap[A, B](p1: StateNoTransition[A])
                            (f: (A) => StateNoTransition[B]): StateNoTransition[B] = p1.flatMap(f)

  override def map[A](a: A): StateNoTransition[A] = (machine)=>a
}
