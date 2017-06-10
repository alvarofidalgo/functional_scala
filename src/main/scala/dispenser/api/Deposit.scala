package dispenser.api

trait Deposit[P[_]] {

  def coins(amount:Int):P[_]

  def flatMap[A,B](p1:P[A])(f:A=>P[B]) :P[B]

  def map[A](a:A):P[A]

}
