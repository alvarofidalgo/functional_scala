package lists

import org.junit.runner.RunWith
import org.scalatest.FlatSpec
import org.scalatest.ShouldMatchers
import conversions.List.withFoldRight
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class ListOperationsFoldRightTest extends FlatSpec with ShouldMatchers{

  " We want to calculate length and result " should " be List length " in new ListNotEmpty {
    val expected = 3
    list.length shouldBe expected
  }


  "We want to implement product  and result " should " be zero if empty list " +
    "" in new Empty {

    val expected = 0
    list.product shouldBe expected
  }

  it should " product all elements if no empty list " in new ListNotEmpty {

    val expected = 6
    list.product shouldBe expected
  }

  "We want to implement sum with foldRight and result " should " be zero if no elements " in new Empty {

    val expected = 0
    list.sum shouldBe expected

  }

  it should " sum of all elements if we have elements " in new ListNotEmpty {

    val expected = 6
    list.sum shouldBe expected

  }

  "We want to reverse list and result " should " be empty list if is empty " in new Empty {
    val expected = MyList()
    list.reverse shouldBe expected
  }

  it should "reverse list if not empty " in new ListNotEmpty{
    val expected = MyList(3,2,1)
    list.reverse shouldBe expected
  }

  "We want to append two list with foldRight and result " should " be list with two elements when two lists hac " +
    "elements " in new
      ListNotEmpty {
    val otherList = MyList[Int](4, 5, 6)
    val expected = MyList[Int](1, 2, 3, 4, 5, 6)
    list.append(otherList) shouldBe expected
  }

  it should " be second List when first list is empty " in new Empty {
    val otherList = MyList[Int](4, 5, 6)
    val expected = MyList[Int](4, 5, 6)
    list.append(otherList) shouldBe expected
  }

  it should " be Nil when two list are empty " in new Empty {

    val otherList = Nil
    val expected = Nil
    list.append(otherList) shouldBe expected
  }
}
