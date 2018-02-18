package monoid

trait Foldable {

  def foldMap[A,B](list: Seq[A], monoid: MonoId[B])(f: A => B): B =
    list.foldLeft(monoid.zero) {
      (res,head) => monoid.op(res,f(head))
  }


  def foldLeft[A,B](list: Seq[A],initialValue:B)(f:(B,A)=>B):B = initialValue

}
