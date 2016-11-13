package errors

case class Option[A](get: A) extends MyOption[A] {

  private def getValue[B, C](default: B, f: (A) => B): B = get match {
    case null => default
    case value => f(value)
  }

  override def map[B](f: (A) => B): MyOption[B] = getValue(None, (get) => Some(get).map(f))

  override def flatMap[B](f: (A) => MyOption[B]): MyOption[B] = getValue(None, (get) => Some(get).flatMap(f))

  override def getOrElse[B >: A](default: => B): B = getValue(default, (get) => get)
}
