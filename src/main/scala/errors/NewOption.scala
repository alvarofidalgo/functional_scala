package errors


trait MyOption[+A] {

    def map[B](f:A=>B):MyOption[B]
}

case class Some[A](get:A) extends MyOption[A] {
   def map[B](f:A=>B):MyOption[B] = Some(f(get))
}

object None extends MyOption[Nothing] {
  def map[B](f:Nothing=>B):MyOption[B] = None
}