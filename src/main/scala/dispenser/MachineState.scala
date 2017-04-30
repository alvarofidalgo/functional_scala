package dispenser


class MachineState {


  private val rules = Seq(StateRule(lowerLimit=0,upperLimit=40,name=PAYMENT),
                          StateRule(lowerLimit=40,upperLimit=Int.MaxValue,name=SELECTED))


  def next(amount:Int):TypeState = {
    val coincidenceRules = rules.filter(rule => amount >=rule.lowerLimit && amount < rule.upperLimit )
    val state = coincidenceRules.head
    state.name
  }

}
