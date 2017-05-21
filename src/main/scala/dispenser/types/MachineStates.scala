package dispenser.types

sealed trait MachineStates

case object Missing extends MachineStates
case object Complete extends MachineStates
