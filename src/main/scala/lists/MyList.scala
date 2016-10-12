package lists

sealed trait MyList[+A]

case object Nil extends MyList[Nothing]

case class Init[+A](head: A, tail: MyList[A]) extends MyList[A]

object MyList {

  def apply[A](as: A*): MyList[A] = as.isEmpty match{
    case true => Nil
    case false =>  Init(as.head, apply(as.tail: _*))
  }
}




