package errors

case class Option[A](get: A)  {

  private val function:A=>Boolean=(a) => a != null

  private def myOptionSelector(f:A=>Boolean) = f(get) match {
      case true => Some(get)
      case false => None
  }

  def map[B](implicit f: (A) => B, fSelector:A=>Boolean = function): MyOption[B] = myOptionSelector(fSelector).map(f)


  def flatMap[B](implicit f: (A) => MyOption[B], fSelector:A=>Boolean = function): MyOption[B] = myOptionSelector(fSelector).flatMap(f)

  def getOrElse[B >: A](default: => B,fSelector:A=>Boolean = function): B = myOptionSelector(fSelector).getOrElse(default)

  def orElse[B >: A](default: => MyOption[B],fSelector:A=>Boolean = function): MyOption[B] =  myOptionSelector(fSelector).orElse(default)

  def filter(f:A=>Boolean):MyOption[A] = myOptionSelector(f).filter(f)
}
