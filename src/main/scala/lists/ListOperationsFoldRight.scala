package lists


import implicits.Defaults._
import conversions.List.recursive


final class ListOperationsFoldRight[A](list:MyList[A]) {


  def length:Int =
    list.foldRight[Int](acc = 0)((a,b)=>b+1)


  def product:Int =
    list.asInstanceOf[MyList[Int]].foldRight[Int](acc = 1)((a, b) => b * a)


  def sum:Int =
    list.asInstanceOf[MyList[Int]].foldRight[Int](acc = 0)((a,b)=>a+b)


  def reverse:MyList[A] =
    list.foldRight[MyList[A]](acc = Nil)((head,reverseList) => MyList[A](head).append(reverseList))

}
