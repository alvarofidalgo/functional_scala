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

    val tree:Tree[Int] = Branch(1,leafRight,leafLeft)
  }

  trait ThreeChild {
    private val leafRight = Leaf[Int](3)
    private  val leafLeft = Leaf[Int](2)
    val first = Branch(10,leafRight,leafLeft)
    val second = Branch(9,leafRight,leafLeft)
    val tree:Tree[Int] = Branch(8,first,second)
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

  "We wan to calculate maximun of Int tree and result " should " be head if only one element " in new SimpleTree{
    val expected = 3
    tree.maximum shouldBe expected
  }

  it should " be maximum of all  nodes when have more nodes " in new ThreeChild{
    val expected = 10
    tree.maximum shouldBe expected
  }

}
