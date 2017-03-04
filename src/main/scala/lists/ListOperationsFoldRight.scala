package lists

import conversions.List.recursive

object ListOperationsFoldRight {

 implicit  final class ListOperationsFoldRight[A](list: MyList[A]) {

    def length: Int =
      list.foldRight[Int](acc = 0)((a, b) => b + 1)

    def product: Int = list match {
      case Nil => 0
      case _ => list.asInstanceOf[MyList[Int]].foldRight[Int](acc = 1)((a, b) => b * a)
    }

    def sum: Int =
      list.asInstanceOf[MyList[Int]].foldRight[Int](acc = 0)((a, b) => a + b)

    def reverse: MyList[A] =
      list.foldRight[MyList[A]](acc = Nil)((head, reverseList) => new ListOperationsFoldRight(MyList[A](head)).append
      (reverseList))

    def append(otherList: MyList[A]): MyList[A] =
      otherList.foldRight[MyList[A]](acc = list)((head, newList) => newList.add(head))
  }

}