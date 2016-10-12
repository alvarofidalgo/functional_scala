package functions

object Partial {

  def compose[A, B, C](f: B => C, g: A => B): A => C = (a) => f(g(a))

  def currying[A, B, C](f: (A, B) => C): A => (B => C) = (a) => (b) => f(a, b)

  def unCurrying[A, B, C](f: A => (B => C)): (A, B) => C = (a, b) => f(a)(b)
}
