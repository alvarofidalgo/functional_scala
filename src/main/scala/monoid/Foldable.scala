package monoid

import scala.annotation.tailrec

trait Foldable {


  @tailrec
  final def foldMap[A,B](list: Seq[A], monoid: MonoId[B])(f: A => B)(implicit result:B = monoid.zero): B =
    list match  {
      case Nil => result
      case  head::tail => foldMap(tail,monoid)(f)( monoid.op(result,f(head)))
    }



  def foldLeft[A,B](list: Seq[A],result:B)(fCombiner:(B,A)=>B)(implicit  monoid: MonoId[B]):B = {
    foldMap(list,monoid){(elem) =>fCombiner(result,elem)}

  }

}
