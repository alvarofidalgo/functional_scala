package parallelims.api



object ParAPI {


  def unit[A](unit: => A):Par[A] = new ParImplement[A](unit)


  def get[A](par:Par[A]):A = par.get


  def map2[A](first:Par[A],second:Par[A])(f:(A,A)=>A):Par[A] = unit(f(first.get,second.get))

}
