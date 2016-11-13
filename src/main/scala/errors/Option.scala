package errors

case class Option[A](get: A) extends MyOption[A] {


  def compose[B,C](f1:(A)=>C,f2:((A)=>C)=> B): B = f2(f1)

  private def getValue [B](default:B,notDefault:B):B = get match {
    case null => default
    case a =>notDefault
  }

  override def map[B](f: (A) => B): MyOption[B] = getValue(None,compose(f,Some(get).map[B]))


  override def flatMap[B](f: (A) => MyOption[B]): MyOption[B] = getValue(None,compose(f,Some(get).flatMap[B]))

  override def getOrElse[B >: A](default: => B): B = getValue(default,get)
}
