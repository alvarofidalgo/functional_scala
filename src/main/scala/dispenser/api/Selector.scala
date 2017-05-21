package dispenser.api

trait Selector[P[_]] {

  def select(amount:Int):P[_]

}
