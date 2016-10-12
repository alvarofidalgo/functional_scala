package lists

import org.scalatest.FlatSpec
import org.scalatest.ShouldMatchers
import conversions.List.withFoldRight

class ListOperationsFoldRightTest extends FlatSpec with ShouldMatchers{

  " We want to calculate length and result " should " be List length " in new ListNotEmpty {
    val expected = 3
    list.length shouldBe expected
  }


  "We want to implement multiply  and result " should " be zero if empty list " +
    "" in new Empty {

    val expected = 0
    list.multiply shouldBe expected
  }

  it should " multiply all elements if no empty list " in new ListNotEmpty {

    val expected = 6
    list.multiply shouldBe expected
  }

  "We want to implement sum with foldRight and result " should " be zero if no elements " in new Empty {

    val expected = 0
    list.sum shouldBe expected

  }

  it should " sum of all elements if we have elements " in new ListNotEmpty {

    val expected = 6
    list.sum shouldBe expected

  }

}
