package lists

import org.scalatest.FlatSpec
import org.scalatest.ShouldMatchers

import conversions.List.withFlatMap

class ListOperationsFlatMapTest extends FlatSpec with ShouldMatchers{


  " We want to implement function filter and result " should " be list without elements that match with function " in new ListNotEmpty {
    val expected = MyList(1, 3)
    list.filter((a) => a == 2) shouldBe expected
  }

  it should " be empty list if match all elements " in new ListNotEmpty {
    val expected = MyList()
    list.filter((a) => a < 4) shouldBe expected
  }
}
