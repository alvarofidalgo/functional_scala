package errors


case class Option[A](get: A) extends MyOption[A]{

  private def myOptionSelector(implicit f: A => Boolean = (a) => a != null) = f(get) match {
    case true => Some(get)
    case false => None
  }

  def map[B](f: (A) => B): MyOption[B] = myOptionSelector.map(f)

  def flatMap[B](f: (A) => MyOption[B]): MyOption[B] = myOptionSelector.flatMap(f)

  def getOrElse[B >: A](default: => B): B = myOptionSelector.getOrElse(default)

  def orElse[B >: A](default: => MyOption[B]): MyOption[B] = myOptionSelector.orElse(default)

  def filter(f: A => Boolean): MyOption[A] = myOptionSelector(f).filter(f)
}
