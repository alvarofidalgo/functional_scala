package errors


sealed trait Either[+E,+A] {
  def map[B](f: (A) => B):Either[E,B]
}
case class Right[+A](value:A) extends Either[Nothing,A]{

   def map[B](f: (A) => B): Either[Nothing, B] = Right(f(value))
}
case class Left[+E](value:E) extends Either[E,Nothing] {

  override def map[B](f: (Nothing) => B): Either[E, B] = Left(value)
}
