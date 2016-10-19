package tree

import scala.annotation.tailrec
import scala.collection.immutable.::

final class TreeOperations[A](tree: Tree[A]) {

  def size: Int =
    walkTree[Int](acc = 1,
                 (numberNodesLeft, b) => numberNodesLeft.last + 1,
                 (numberNodesRight, b) => numberNodesRight.last + 2)

  def maximum(f:(A,A)=>A): A =
    walkTree[A](acc = tree.value,
                  (maxLeft, value) => f(maxLeft.head,value),
                  (maxRight, value) => f(maxRight.head,value))

  def depth:Int =
    walkTree[Int](acc = 1,(acc,value) => acc.head + 1,(acc,value)=>acc.head + 1)


  @tailrec
  def walkTree[B](acc: B , fLeft: (Seq[B], A) => B,fRight:(Seq[B],A)=>B)
                 (implicit parentNodes: Seq[B] = Seq(acc),trees: Seq[Tree[A]] = Seq(tree)): B = trees match {
    case Nil => acc
    case Leaf(value) :: tail =>
      walkTree(parentNodes.head,fLeft,fRight)(parentNodes.tail, tail)
    case Branch(value, left, right) :: tail =>
      val leftValue = fLeft(parentNodes,left.value)
      val rightValue = fRight(parentNodes,right.value)
      val nodes = parentNodes.tail ++ Seq(leftValue, rightValue)
      walkTree(parentNodes.head,fLeft,fRight) (nodes, tail ++ Seq(left, right))
  }
}
