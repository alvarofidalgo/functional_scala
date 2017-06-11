package parallelims.api

sealed trait Par[A] {

  def get:A
}


class ParImplement[A](unit: => A) extends Par[A] {


  override def get: A = unit
}


