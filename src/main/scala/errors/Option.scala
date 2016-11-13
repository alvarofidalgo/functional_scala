package errors

//TODO : IN REFACTOR
case class Option[A](get: A) extends MyOption[A] {


  def myFunction[B,C](f1:(A)=>C,f2:((A)=>C)=> MyOption[B]): MyOption[B] = f2(f1)

  private def otherFunction [B,C,D](default:MyOption[B],notDefuaul:MyOption[B]) = get match {
    case null => None
    case a =>notDefuaul
  }

  override def map[B](f: (A) => B): MyOption[B] = otherFunction(None,myFunction(f,Some(get).map[B]))


  override def flatMap[B](f: (A) => MyOption[B]): MyOption[B] = otherFunction(None,myFunction(f,Some(get).flatMap[B]))

  override def getOrElse[B >: A](default: => B): B = get match {
    case null => default
    case a => get
  }
}
