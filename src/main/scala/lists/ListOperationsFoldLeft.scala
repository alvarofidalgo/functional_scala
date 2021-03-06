package lists


import functions.Partial
import Partial._

object ListOperationsFoldLeft {

  implicit final class ListOperationsFoldLeft[A](list: MyList[A]) extends Transforms[A] {
    import lists.ListOperationsRecursive._
    def length: Int =
      list.foldLeft(acc = 0)((a, b) => a + 1)

    def product: Int = list match {
      case Nil => 0
      case _ => list.asInstanceOf[MyList[Int]].foldLeft[Int](acc = 1)((a, b) => b * a)
    }

    def sum: Int =
      list.asInstanceOf[MyList[Int]].foldLeft[Int](acc = 0)((a, b) => a + b)

    def reverse: MyList[A] =
      list.foldLeft[MyList[A]](acc = Nil)((reverseList, head) =>
        new ListOperationsFoldLeft(MyList[A](head)).append(reverseList))

    def foldRight[B](acc: B)(f: (A, B) => B): B =
      list.foldLeft[B](acc)(unCurrying((a) => (b) => f(b, a)))

    def append(otherList: MyList[A]): MyList[A] =
      otherList.foldLeft[MyList[A]](acc = list)((newList, head) => newList.add(head))

    def flatten: MyList[A] =
      list.foldLeft[MyList[A]](acc = MyList[A]())((newList, head) =>
        new ListOperationsFoldLeft( newList).append(headList(head))
      )

    def map[B](f: (A) => B): MyList[B] =
      list.foldLeft[MyList[B]](acc = MyList[B]())((newList, head) => newList.add(f(head)))

    def filter(f: (A) => Boolean): MyList[A] =
      list.foldLeft[MyList[A]](acc = MyList[A]())((newList, head) =>
        if (!f(head)) newList.add(head) else newList
      )

    def flatMap(f: (A) => MyList[A]): MyList[A] =
      list.foldLeft[MyList[A]](acc = MyList[A]())((newList, head) =>
        new  ListOperationsFoldLeft(newList).append(f(head)))
  }

}
