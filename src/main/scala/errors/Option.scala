package errors

case class Option[A](get: A) extends MyOption[A] {

  private val myOption = get match {
    case null => None
    case value => Some(get)
  }

  def map[B](f: (A) => B): MyOption[B] = myOption.map(f)

  def flatMap[B](f: (A) => MyOption[B]): MyOption[B] = myOption.flatMap(f)

  def getOrElse[B >: A](default: => B): B = myOption.getOrElse(default)

  def orElse[B >: A](default: => MyOption[B]): MyOption[B] =  myOption.orElse(default)
}
