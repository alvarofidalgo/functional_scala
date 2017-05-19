package dispenser


class CoffeeMachine[A](amount:A,price:A) {



  def calculateAccAmount(amount: A):A =
    amount match {
      case i: Int => (this.amount.asInstanceOf[Int] + i).asInstanceOf[A]
      case i: Double => (this.amount.asInstanceOf[Double] + i).asInstanceOf[A]
      case i: String =>   (this.amount.asInstanceOf[String].toInt + i).asInstanceOf[A]
    }


  def calculateMissingAmount(amount: A):Int =
    amount match {
      case i: Int => this.price.asInstanceOf[Int] - i
      case i: Double => (this.price.asInstanceOf[Double] - i).asInstanceOf[Int]
      case i: String =>   this.price.asInstanceOf[String].toInt - i.toInt
    }


  def insert(amount:A):CoffeeMachine[A] = {
    val accAmount = calculateAccAmount(amount)
    new CoffeeMachine[A](accAmount,price)
  }

  def select():String = {

    val amountMissingAcc = calculateMissingAmount(amount = this.amount)

    if (amountMissingAcc > 0)
    s"$amountMissingAcc cents is missing"
    else
      "your coffee"
  }
}