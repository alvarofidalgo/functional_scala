package dispenser


object StateTransition {

  def state(amount:Int,price:Int):MachineStates = {
    amount compare 40 match {
      case -1 => Missing
      case _=>   Complete
    }
  }

}
