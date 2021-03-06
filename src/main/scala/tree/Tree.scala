package tree


sealed trait Tree [+A]{
   val value:A
}

case class Leaf[A](value:A)extends Tree[A]

case class Branch[A](value:A,left:Tree[A],right: Tree[A]) extends Tree[A]

