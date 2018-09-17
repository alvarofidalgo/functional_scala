package monoid

import scala.annotation.tailrec
//TODO : LAS EXERCICES WAS 10.6 , NEXT CHAPTER SHOULD BE 10.3
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


  def foldRight[B](result:B)(fCombiner:(A,B)=>B)(implicit  monoid: MonoId[B]):B = {
    val copySeq = seq
    new  Foldable[A] {
       def seq = copySeq.reverse
    }.
    foldMap(monoid){(elem) =>fCombiner(elem,result)}
  }


  def foldMapV[B]( monoid: MonoId[B])(f: A => B):B = seq match {
    case Nil => monoid.zero
    case head::Nil => monoid.op(f(head),monoid.zero)
  }



}


case class FoldableSeq[A] (seq:Seq[A]) extends Foldable[A]
