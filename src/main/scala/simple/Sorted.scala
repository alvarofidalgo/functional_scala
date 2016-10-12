package simple

import scala.annotation.tailrec

object Sorted {

  def isSorted[A](array: Array[A],ordered: (A,A)=>Boolean):Boolean = {
   @tailrec
   def isSorted(array: Array[A],result:Boolean ) : Boolean = array.length match {
     case 1 => result
     case _ =>
       val tail = array.tail
       isSorted(tail,result && ordered(array.head,tail.head))
   }

   isSorted(array,true)
  }
}
