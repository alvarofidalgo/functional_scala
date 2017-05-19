package dispenser



class CoffeeMachine(amount:Int) {

  def insert(amount:Int):CoffeeMachine = {
    new CoffeeMachine(this.amount + amount)
  }

  def select():String = {

    val amountMissing = 40 -amount
    s"$amountMissing cents is missing"
  }
}