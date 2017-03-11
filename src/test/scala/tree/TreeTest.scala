package tree


import org.junit.runner.RunWith
import org.scalatest.FlatSpec
import org.scalatest.ShouldMatchers
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class TreeTest extends FlatSpec with ShouldMatchers {

  trait SimpleTree {

    val tree: Tree[Int] = Leaf[Int](1)
  }

  trait TwoChild {

    private val two = Leaf[Int](3)
    private val three = Leaf[Int](2)

    val tree: Tree[Int] = Branch(1, two, three)
  }

  trait ThreeChild {

    private val six = Leaf(6)
    private val seven = Leaf(7)
    private val four = Leaf(4)
    private val five = Leaf(5)
    private val two = Branch(2, four, five)
    private val three = Branch(3, six, seven)
    val tree: Tree[Int] = Branch(1, two, three)
  }

  trait LongDifferentDeph {

    private val twelve = Leaf(12)
    private val thirteen = Leaf(13)
    private val ten = Branch(10, twelve, thirteen)
    private val eighth = Leaf(8)
    private val nine = Leaf(9)
    private val eleven = Leaf(11)
    private val four = Leaf(4)
    private val five = Branch(5, eighth, nine)
    private val six = Branch(6, ten, eleven)
    private val seven = Leaf(7)
    private val two = Branch(2, four, five)
    private val three = Branch(3, six, seven)
    val tree: Tree[Int] = Branch(1, two, three)
  }
  import tree.TreeOperations._
  " We want to calculate size tree and result " should " be one node if tree is simple " in new SimpleTree {
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

  "We wan to calculate maximun of Int tree and result " should " be head if only one element " in new SimpleTree {
    val expected = 1
    tree.maximum((maximum, value) => maximum.max(value)) shouldBe expected
  }

  it should " be maximum of all  nodes when have more nodes " in new ThreeChild {
    val expected = 7
    tree.maximum((maximum, value) => maximum.max(value)) shouldBe expected
  }

  "We want to calculate depth and result " should " be one when only have one node " in new SimpleTree {
    val expected = 1
    tree.depth shouldBe expected
  }

  it should " be two when have two children" in new TwoChild {
    val expected = 2
    tree.depth shouldBe expected
  }

  it should " be three when have three child " in new ThreeChild {
    val expected = 3
    tree.depth shouldBe expected
  }

  it should " be maximum depth when exist more " in new LongDifferentDeph {
    val expected = 5
    tree.depth shouldBe expected
  }
}
