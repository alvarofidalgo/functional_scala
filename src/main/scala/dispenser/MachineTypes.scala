package dispenser


object MachineTypes {

  type MachineTransition[T] = (MachineState) => (MachineState,T)
}
