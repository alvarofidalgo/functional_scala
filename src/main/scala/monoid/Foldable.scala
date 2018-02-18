package monoid

import scala.annotation.tailrec

trait Foldable {

  def foldMap[A,B](list: Seq[A], monoid: MonoId[B])(f: A => B): B =
    list.foldLeft(monoid.zero) {
      (res,head) => monoid.op(res,f(head))
  }

  @tailrec
  final def foldLeft[A,B](list: Seq[A],initialValue:B)(fCombiner:(B,A)=>B):B = list match  {
    case Nil => initialValue
    case  head::tail => foldLeft(tail,fCombiner(initialValue,head))(fCombiner)
  }


}
