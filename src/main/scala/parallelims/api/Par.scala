package parallelims.api




class ParImplement[A](unit: => A) extends Future[A] {


  override def get: A = unit
}


