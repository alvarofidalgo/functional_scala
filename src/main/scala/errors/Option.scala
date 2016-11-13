package errors

case class Option[A](get: A) extends MyOption[A] {

  private def getValue[B](fDefault: () => B, f: (MyOption[A]) => B): B = get match {
    case null => fDefault()
    case value => f(Some(value))
  }

  def map[B](f: (A) => B): MyOption[B] =
    getValue(() => None.map(f), (get) => get.map(f))

  def flatMap[B](f: (A) => MyOption[B]): MyOption[B] =
    getValue(() => None.flatMap(f), (get) => get.flatMap(f))

  def getOrElse[B >: A](default: => B): B =
    getValue(() => None.getOrElse(default), (get) => get.getOrElse(default))

  def orElse[B >: A](default: => MyOption[B]): MyOption[B] =
    getValue(() => None.orElse(default), (get) => get.orElse(default))
}
