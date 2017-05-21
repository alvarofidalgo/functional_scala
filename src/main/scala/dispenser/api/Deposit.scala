package dispenser.api

trait Deposit[P[_]] {

  def coins(amount:Int):P[_]

}
