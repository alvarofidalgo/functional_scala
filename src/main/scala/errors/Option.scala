package errors

case class Option [A](get:A) extends MyOption[A]{

  override def map[B](f: (A) => B): MyOption[B] = get match {
    case null => None
    case a => Some(get).map(f)
  }


  override def flatMap[B](f: (A) => MyOption[B]): MyOption[B] = ???

  override def getOrElse[B >: A](default: => B): B = ???
}
