package dispenser.types

import dispenser.MachineState

object MachineTypes {

  type MachineTransition[T] = (MachineState) => (MachineState,T)

  type StateNoTransition[T] = (MachineState) => T
}
