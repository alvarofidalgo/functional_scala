package dispenser

import types.StateTypes._


case class Machine(amount:Int)



case object Machine{

  def insert(money:Int,mode:Modes):State[Machine,(TypeState)] = {

    (machine) =>
      val value = if (mode==INSERT ) machine.amount   + money else  machine.amount   - money
      val newState = if (mode==INSERT ) new MachineState().next(value) else RETURN
      (newState,Machine(value))

  }

}