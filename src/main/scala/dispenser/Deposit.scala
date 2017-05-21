package dispenser


trait Deposit[P[_]] {

  def select(amount:Int):P[Int]

}
