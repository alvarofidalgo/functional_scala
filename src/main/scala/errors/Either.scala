package errors


sealed trait Either[+E,+A] {
  def map[B](f: (A) => B):Either[E,B]
}
case class Right[+E](value:E) extends Either[E,Nothing]{

  override def map[B](f: (Nothing) => B): Either[E, B] = ???
}
case class Left[+A](value:A) extends Either[Nothing,A] {

  override def map[B](f: (A) => B):Either[Nothing,B] = Left(f(value))
}
