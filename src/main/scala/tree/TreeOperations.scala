package tree

import scala.annotation.tailrec
import scala.collection.immutable.::

final class TreeOperations[A](tree: Tree[A]) {


  def size: Int =
    walkTree[Int](initialValue = 0)((numberNodes,b)=> numberNodes + 1)


  def maximum: Int =
    walkTree[Int](initialValue = 0)((max,value)=>max.max(value.asInstanceOf[Int]))


  def depth :Int = {
     val fLeaf:(Int,A) => Int = (max,value)=>max.max(value.asInstanceOf[Int])
     val fBranch:Option[(Int,A) => Int] = Some((numberNodes,b)=> numberNodes + 1)
     walkTree[Int](initialValue = 0)(fLeaf,fBranch)
  }


  @tailrec
  private def walkTree[B](initialValue: B, trees: Seq[Tree[A]] = Seq(tree))(fLeaf: (B, A) => B,fBranch:Option[(B,A) =>B]=None): B =
    trees match {
    case Nil => initialValue
    case Leaf(value) :: tail => walkTree(fLeaf(initialValue, value), tail)(fLeaf,fBranch)
    case Branch(value, left, right) :: tail => {
      val fBranchExec = fBranch.getOrElse(fLeaf)
      val trees = Seq(left, right) ++ tail
      walkTree(fBranchExec(initialValue, value),trees)(fLeaf,fBranch)
    }
  }
}
