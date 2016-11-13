package errors

case class Option[A](get: A) extends MyOption[A] {

  private def getValue[B, C](default: B, fParameter: (A) => C, fCompose: ((A) => C) => B): B = get match {
    case null => default
    case a => fCompose(fParameter)
  }

  override def map[B](f: (A) => B): MyOption[B] = getValue(None, f, Some(get).map[B])

  override def flatMap[B](f: (A) => MyOption[B]): MyOption[B] = getValue(None, f, Some(get).flatMap[B])

  override def getOrElse[B >: A](default: => B): B = getValue[B, B](default, (a) => {
    get
  }, (a) => {
    get
  })
}
