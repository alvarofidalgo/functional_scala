package tree

import scala.annotation.tailrec
import scala.collection.immutable.::

final class TreeOperations[A](tree: Tree[A]) {

  def size: Int =
    walkTree[Int](acc = 1,
                 (numberNodes, b) => numberNodes.last + 1,
                 (numberNodes, b) => numberNodes.last + 2)

  def maximum: Int =
    walkTree[Int](acc = tree.value.asInstanceOf[Int],
                  (max, value) => max.head.max(value.asInstanceOf[Int]),
                  (max, value) => max.head.max(value.asInstanceOf[Int]))

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
