package monoid

import scala.annotation.tailrec

sealed trait Foldable[A] {


  def foldMap[B]( monoid: MonoId[B])(f: A => B)(implicit result:B = monoid.zero): B
  def foldLeft[B](result:B)(fCombiner:(B,A)=>B)(implicit  monoid: MonoId[B]):B

}


case class FoldableSeq[A] (seq:Seq[A]) extends Foldable[A] {

  @tailrec
  override final def foldMap[B]( monoid: MonoId[B])(f: A => B)(implicit result:B = monoid.zero): B =
    seq match  {
      case Nil => result
      case  head::tail => FoldableSeq(tail).foldMap(monoid)(f)( monoid.op(result,f(head)))
    }


  def foldLeft[B](result:B)(fCombiner:(B,A)=>B)(implicit  monoid: MonoId[B]):B = {

    foldMap(monoid){(elem) =>fCombiner(result,elem)}

  }
}
