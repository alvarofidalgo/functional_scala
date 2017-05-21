package dispenser


object MachineTypes {

  type MachineTransition[T] = (CoffeeMachine) => (CoffeeMachine,T)
}
