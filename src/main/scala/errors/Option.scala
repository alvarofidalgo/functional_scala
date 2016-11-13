package errors

//TODO : IN REFACTOR
case class Option[A](get: A) extends MyOption[A] {


  def myFunction[B,C](f1:(A)=>C,f2:((A)=>C)=> MyOption[B]) = f2(f1)

  override def map[B](f: (A) => B): MyOption[B] = get match {
    case null => None
    case a =>myFunction(f,Some(get).map[B])
  }

  override def flatMap[B](f: (A) => MyOption[B]): MyOption[B] = get match {
    case null => None
    case a => myFunction(f,Some(get).flatMap[B])
  }

  override def getOrElse[B >: A](default: => B): B = get match {
    case null => default
    case a => get
  }
}
