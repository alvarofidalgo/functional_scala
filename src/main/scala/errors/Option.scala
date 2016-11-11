package errors

//TODO : DELETE DRY IN CASE NULL
case class Option [A](get:A) extends MyOption[A]{

  override def map[B](f: (A) => B): MyOption[B] = get match {
    case null => None
    case a => Some(get).map(f)
  }


  override def flatMap[B](f: (A) => MyOption[B]): MyOption[B] = get match {
    case null => None
    case a => Some(get).flatMap(f)
  }

  override def getOrElse[B >: A](default: => B): B = get match {
    case null => default
    case a => get
  }
}
