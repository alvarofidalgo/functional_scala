package streams


sealed trait Streams[+A]

case object Empty extends Streams[Nothing]

case class InitStreams[+A](head: () => A, tail: () => Streams[A]) extends Streams[A]

object Streams {


  def empty[A]: Streams[A] = Empty

  def apply[A](as: A*): Streams[A] = as.isEmpty match {
    case true => Empty
    case false =>
      lazy val head = () => as.head
      lazy val tail = () => apply(as.tail: _*)
      InitStreams(head, tail)
  }
}



