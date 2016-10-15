package tree

import scala.annotation.tailrec

final class TreeOperations [A](tree:Tree[A]){

  @tailrec
  def size(implicit numberNodes:Int = 1,trees:Seq[Tree[A]]=Seq(tree)):Int = trees match {
    case Nil => numberNodes
    case Leaf(a)::tail =>size(numberNodes ,tail)
    case Branch(left,right)::tail => size(numberNodes + 2,Seq(left,right)++tail)
  }
}
