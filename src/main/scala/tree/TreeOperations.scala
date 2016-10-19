package tree

import scala.annotation.tailrec
import scala.collection.immutable.::

final class TreeOperations[A](tree: Tree[A]) {

  def size: Int =
    walkTree[Int](initialValue = 0)((numberNodes, b) => numberNodes + 1)

  def maximum: Int =
    walkTree[Int](initialValue = 0)((max, value) => max.max(value.asInstanceOf[Int]))


  @tailrec
  def depth(implicit maxNodes: Int = 0,
            parentNodes: Seq[Int] = Seq(0),
            trees: Seq[Tree[A]] = Seq(tree)): Int = trees match {
    case Nil => maxNodes
    case Leaf(value) :: tail =>
      depth(parentNodes.head +1, parentNodes.tail, tail)
    case Branch(value, left, right) :: tail =>
      val nextDepth = parentNodes.head + 1
      depth(maxNodes, parentNodes.tail ++ Seq(nextDepth,nextDepth), tail ++ Seq(left, right))
  }

  @tailrec
  private def walkTree[B](initialValue: B, trees: Seq[Tree[A]] = Seq(tree))(f: (B, A) => B): B =
    trees match {
      case Nil => initialValue
      case Leaf(value) :: tail => walkTree(f(initialValue, value), tail)(f)
      case Branch(value, left, right) :: tail =>
        val trees = Seq(left, right) ++ tail
        walkTree(f(initialValue, value), trees)(f)
    }
}
