package errors


trait MyOption[+A] {

    def map[B](f:A=>B):MyOption[B]

    def flatMap[B](f:A=>MyOption[B]):MyOption[B]

    def getOrElse[B >: A](default : => B) : B
}

case class Some[A](get:A) extends MyOption[A] {

   def map[B](f:A=>B):MyOption[B] = Some(f(get))

   def flatMap[B](f:A=>MyOption[B]):MyOption[B] = f(get)

   def getOrElse[B >: A](default : => B) : B = get
}

object None extends MyOption[Nothing] {

  def map[B](f:Nothing=>B):MyOption[B] = None

  def flatMap[B](f:Nothing=>MyOption[B]):MyOption[B] = None

  def getOrElse[B](default : => B) : B  = default
}