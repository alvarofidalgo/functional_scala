package tree

import org.scalatest.FlatSpec
import org.scalatest.ShouldMatchers

import conversions.Trees._

class TreeTest extends FlatSpec with ShouldMatchers{


  trait SimpleTree {
     val tree:Tree[Int] = Leaf[Int](3)
  }

  trait TwoChild {
    private val leafRight = Leaf[Int](3)
    private  val leafLeft = Leaf[Int](2)

    val tree:Tree[Int] = Branch(leafRight,leafLeft)
  }

  trait ThreeChild {
    private val leafRight = Leaf[Int](3)
    private  val leafLeft = Leaf[Int](2)
    val first = Branch(leafRight,leafLeft)
    val second = Branch(leafRight,leafLeft)
    val tree:Tree[Int] = Branch(first,second)
  }


  " We want to calculate size tree and result " should " be one node if tree is simple " in new SimpleTree{
    val expected = 1
    tree.size shouldBe expected
  }

  it should " be three if tree  one two  child " in new TwoChild {
    val expected = 3
    tree.size shouldBe expected
  }

  it should "be seven when have three child " in new ThreeChild {
    val expected = 7
    tree.size shouldBe expected
  }


 /*"We want to retrieve child of branch and result " should " be NilTree if no child "  in new SimpleTree{
    val expeted = NilTree
    tree.childs shoudlBe expeted
  }*/
}
