package parallelims.api


object ParAPI {


  def unit[A](unit: => A):Par[A] = new ParImplement[A](unit)


  def get[A](par:Par[A]):A = par.get

}
