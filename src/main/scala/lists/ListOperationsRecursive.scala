package lists

import scala.annotation.tailrec

import conversions.List.recursive

// TODO : make isContain function
final class ListOperationsRecursive[A](list: MyList[A]) extends Transforms[A] {

  @tailrec
  def append(otherList: MyList[A]): MyList[A] = otherList match {
    case Nil => list
    case Init(head, tail) => list.add(head).append(tail)
  }

  def add(element: A): MyList[A] = Init(element, list.reverse).reverse

  @tailrec
  def reverse(implicit result: MyList[A] = Nil): MyList[A] = list match {
    case Nil => result
    case Init(head, tail) => tail.reverse(Init(head, result))
  }

  @tailrec
  def drop(elements: Int): MyList[A] = elements match {
    case 0 => list
    case _ => list.tail.drop(elements - 1)
  }

  @tailrec
  def dropWhile(predicate: (A) => Boolean): MyList[A] = newPredicate(predicate, list.head) match {
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
  def length(implicit counter: Int = 0): Int = list match {
    case Nil => counter
    case Init(head, tail) => tail.length(1 + counter)
  }

  def modify(head: A): MyList[A] = list match {
    case Nil => Nil
    case Init(lastHead, tail) => Init(head, tail)
  }

  def tail: MyList[A] = list match {
    case Nil => Nil
    case Init(head, tail) => tail
  }

  def init: MyList[A] = list.reverse().tail.reverse()

  @tailrec
  def foldRight[B](acc: B)(f: (A, B) => B): B = list match {
    case Nil => acc
    case Init(head, tail) => tail.foldRight(f(head, acc))(f)
  }

  @tailrec
  def foldLeft[B](acc: B)(f: (B, A) => B): B = list match {
    case Nil => acc
    case Init(head, tail) => tail.foldLeft(f(acc, head))(f)
  }

  @tailrec
  def flatten(implicit result: MyList[A] = MyList[A]()): MyList[A] = list match {
    case Nil => result
    case Init(head, tail) =>
      tail.flatten(result.append(headList(head)))
  }

  @tailrec
  def map[B](f: (A) => B)(implicit result: MyList[B] = MyList[B]()): MyList[B] = list match {
    case Nil => result
    case Init(head, tail) => tail.map[B](f)(result.add(f(head)))
  }

  @tailrec
  def filter(f: (A) => Boolean)(implicit result: MyList[A] = MyList[A]()): MyList[A] = list match {
    case Nil => result
    case Init(head, tail) =>
      val newList = if (!f(head)) result.add(head) else result
      tail.filter(f)(newList)
  }

  @tailrec
  def flatMap(f: (A) => MyList[A])(implicit result: MyList[A] = MyList[A]()): MyList[A] = list match {
    case Nil => result
    case Init(head, tail) => tail.flatMap(f)(result.append(f(head)))
  }

  def zipWith[B,C](other : MyList[B],f:(A,B)=>C)(implicit result:MyList[C] = MyList[C]()) : MyList[C] = list match {
    case Nil => result
    case Init(head,tail)=> tail.zipWith(other.tail,f)(result.add(f(head,other.head.get)))
  }
}
