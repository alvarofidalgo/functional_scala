package dispenser


class MachineState {


  def next(amount:Int):String = {
    if ((amount==20) || (amount==39))
      "PAYMENT"
    else if (amount == 40)
      "SELECTED"
    else
      "UNKNOWN"
  }
}
