package parallelims.api



object ParAPI {


  def unit[A](unit: => A):Par[A] = new ParImplement[A](unit)


  def map2[A,B,C](first:Par[A],second:Par[B])(f:(A,B)=>C):Par[C] = unit(f(first.get,second.get))


  def fork[A](a: => Par[A]):Par[A] = unit(a.get)


}
