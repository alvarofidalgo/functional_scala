package tree

import scala.annotation.tailrec
import scala.collection.immutable.::

final class TreeOperations[A](tree: Tree[A]) {


  def size: Int = walkTree[Int](initialValue = 0)((numberNodes,b)=> numberNodes + 1)

  def maximum: Int = walkTree[Int](initialValue = 0)((max,value)=>max.max(value.asInstanceOf[Int]))


  @tailrec
  private def walkTree[B](initialValue: B, trees: Seq[Tree[A]] = Seq(tree))(f: (B, A) => B): B = trees match {
    case Nil => initialValue
    case Leaf(value) :: tail => walkTree(f(initialValue, value), tail)(f)
    case Branch(value, left, right) :: tail => walkTree(f(initialValue, value), Seq(left, right) ++ tail)(f)
  }
}
