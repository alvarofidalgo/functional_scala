package dispenser

import types.StateTypes._


case class Machine(amount:Int)


case object Machine{

  def insert(money:Int):State[Machine,(TypeState)] = {

    (machine) =>
      val value = machine.amount   + money
      val newState = new MachineState().next(value)
      (newState,Machine(value))

  }
}