package dispenser.types

import dispenser.types.MachineTypes.StateNoTransition



object StateNoTransitionOperations {


  implicit class Operations[T](noTrans:StateNoTransition[T]) {

    def flatMap[B](f:T=>StateNoTransition[B]):StateNoTransition[B] = (machine) => f(noTrans(machine))(machine)


    def map[B](f: T => B): StateNoTransition[B] = (machine) => f(noTrans(machine))

  }

}
