package lists

import conversions.List.recursive
import implicits.Defaults._
import simple.Partial._

final class ListOperationsFoldLeft[A](list: MyList[A]) {

  def length: Int =
    list.foldLeft(acc = 0)((a, b) => a + 1)

  def product: Int =
    list.asInstanceOf[MyList[Int]].foldLeft[Int](acc = 1)((a, b) => b * a)

  def sum: Int =
    list.asInstanceOf[MyList[Int]].foldLeft[Int](acc = 0)((a, b) => a + b)

  def reverse: MyList[A] =
    list.foldLeft[MyList[A]](acc = Nil)((reverseList, head) => MyList[A](head).append(reverseList))

  def foldRight[B](acc: B)(f: (A, B) => B)(implicit default: B): B =
    list.foldLeft[B](acc)(unCurrying((a) => (b) => f(b, a)))
}
