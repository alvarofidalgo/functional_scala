package conversions

import tree.Tree
import tree.TreeOperations

object Trees {

  implicit def treeConversion[A](tree:Tree[A]):TreeOperations[A] = new TreeOperations[A](tree)
}
