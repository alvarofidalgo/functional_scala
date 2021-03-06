package dispenser.api

trait Machine[P[_]] {

  def insert(amount:Int):P[Int]

  def select():P[String]
}
