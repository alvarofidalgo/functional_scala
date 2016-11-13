package errors

case class Option[A](get: A) extends MyOption[A] {

  private def getValue[B, C](fDefault: () => B, f: (A) => B): B = get match {
    case null => fDefault()
    case value => f(value)
  }

  def map[B](f: (A) => B): MyOption[B] =

    getValue(() => None.map(f), (get) => Some(get).map(f))

  def flatMap[B](f: (A) => MyOption[B]): MyOption[B] =
    getValue(() => None.flatMap(f), (get) => Some(get).flatMap(f))

  def getOrElse[B >: A](default: => B): B =
    getValue(() => None.getOrElse(default), (get) => Some(get).getOrElse(default))

  def orElse[B >: A](default: => MyOption[B]): MyOption[B] =
    getValue(() => None.orElse(default), (get) => Some(get).orElse(default))
}
