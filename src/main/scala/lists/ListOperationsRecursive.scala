package lists

import scala.annotation.tailrec
import conversions.List._


class ListOperationsRecursive[A](list: MyList[A]) {


  @tailrec
  final def append (otherList: MyList[A]): MyList[A] =otherList match {
    case Nil => list
    case Init(head,tail) => list.add(head).append(tail)
  }


  final def add(element:A) :MyList[A] = Init(element, list.reverse).reverse

  @tailrec
  final def reverse(implicit result: MyList[A] = Nil): MyList[A] = list match {
    case Nil => result
    case Init(head,tail) => tail.reverse(Init(head,result))
  }

  @tailrec
  final def drop(elements: Int): MyList[A] = elements match {
    case 0 => list
    case _ => list.tail.drop(elements - 1)
  }

  @tailrec
  final def dropWhile(predicate: (A) => Boolean): MyList[A] = newPredicate(predicate, list.head) match {
    case false => list
    case true => list.tail.dropWhile(predicate)
  }

  private def newPredicate(predicate: (A) => Boolean, head: Option[A]): Boolean = head match {
    case None => false
    case _ => predicate(head.get)
  }

  def head: Option[A] = list match {
    case Nil => None
    case Init(head, tail) => Some(head)
  }

  @tailrec
  final def length(implicit counter:Int = 0): Int = list match {
    case Nil => counter
    case Init(head, tail) => tail.length(1+counter)
  }

  def lengthWithFoldRight : Int = {
    import implicits.Accumulators.initSumAcc
    val counter:(A,Int)=>(Int) = (a,b) => b +1
    list.foldRight[Int](0)(counter)
  }

  def modify(head: A): MyList[A] = list match {
    case Nil => Nil
    case Init(lastHead, tail) => Init(head, tail)
  }

  def tail: MyList[A] = list match {
    case Nil => Nil
    case Init(head, tail) => tail
  }

  def init : MyList[A] = list.reverse().tail.reverse()


  @tailrec
  final def foldRight[B](default: B)(f: (A, B) => B)(implicit acc:B): B = list match {
    case Nil =>default
    case Init(head, tail) =>
      val result = f(head,acc)
      tail.foldRight(result)(f)(result)
  }
}
