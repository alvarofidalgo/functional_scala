package dispenser.api


trait Message[P[_]] {

  def messageShow(amount:Int):P[_]

}
