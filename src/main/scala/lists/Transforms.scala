package lists

trait Transforms[A] {

  final def headList(head: A): MyList[A] = head match {
    case Init(he, tail) => head.asInstanceOf[MyList[A]]
    case _ => MyList[A](head)
  }
}
