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
            parentNodes: Seq[(Int, Tree[A])] = Seq((0, tree)),
            trees: Seq[Tree[A]] = Seq(tree)): Int = trees match {
    case Nil => maxNodes
    case Leaf(value) :: tail =>
      depth(parentNodes.head._1 +1, parentNodes.tail, tail)
    case Branch(value, left, right) :: tail =>
      val actualDepth = parentNodes.head._1 + 1
      val parents = calculateParents(Branch(value, left, right), parentNodes) ++ Seq((actualDepth, left), (actualDepth, right))
      depth(actualDepth, parents, tail ++ Seq(left, right))
  }

  private def calculateParents(child: Branch[A], parents: Seq[(Int, Tree[A])]): Seq[(Int, Tree[A])] = parents.head match {
    case (_, Branch(_, `child`, _)) | (_, Branch(_, _, `child`)) => parents
    case _ => parents.tail
  }

  @tailrec
  private def walkTree[B](initialValue: B, trees: Seq[Tree[A]] = Seq(tree))(fLeaf: (B, A) => B): B =
    trees match {
      case Nil => initialValue
      case Leaf(value) :: tail => walkTree(fLeaf(initialValue, value), tail)(fLeaf)
      case Branch(value, left, right) :: tail =>
        val trees = Seq(left, right) ++ tail
        walkTree(fLeaf(initialValue, value), trees)(fLeaf)
    }
}
