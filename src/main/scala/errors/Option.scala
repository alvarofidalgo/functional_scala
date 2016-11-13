package errors

case class Option[A](get: A) extends MyOption[A] {

  private def getValue[B](f: (MyOption[A]) => B): B = get match {
    case null => f(None)
    case value => f(Some(value))
  }

  def map[B](f: (A) => B): MyOption[B] =
    getValue((get) => get.map(f))

  def flatMap[B](f: (A) => MyOption[B]): MyOption[B] =
    getValue((get) => get.flatMap(f))

  def getOrElse[B >: A](default: => B): B =
    getValue((get) => get.getOrElse(default))

  def orElse[B >: A](default: => MyOption[B]): MyOption[B] =
    getValue((get) => get.orElse(default))
}
