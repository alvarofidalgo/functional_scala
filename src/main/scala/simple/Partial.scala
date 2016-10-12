package simple

object Partial {

  def compose[A, B, C](f: B => C, g: A => B): A => C = (a) => f(g(a))

  def curryn[A, B, C](f: (A, B) => C): A => (B => C) = (a) => (b) => f(a, b)

  def unCurryng[A, B, C](f: A => (B => C)): (A, B) => C = (a, b) => f(a)(b)
}
