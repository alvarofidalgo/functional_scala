package monoid

import scala.annotation.tailrec

sealed trait Foldable[A] {

  def seq:Seq[A]

  @tailrec
  final def foldMap[B]( monoid: MonoId[B])(f: A => B)(implicit result:B = monoid.zero,s:Seq[A] = seq): B =
  s match  {
    case Nil => result
    case  head::tail => foldMap(monoid)(f)( monoid.op(result,f(head)),tail)
  }

  def foldLeft[B](result:B)(fCombiner:(B,A)=>B)(implicit  monoid: MonoId[B]):B =

    foldMap(monoid){(elem) =>fCombiner(result,elem)}

}


case class FoldableSeq[A] (seq:Seq[A]) extends Foldable[A]
