package monoid

trait MonoId[A] {

  def op(a:A,b:A):A
  def zero:A
}
