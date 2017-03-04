package lists

import org.junit.runner.RunWith
import org.scalatest.FlatSpec
import org.scalatest.ShouldMatchers


import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class ListOperationsFlatMapTest extends FlatSpec with ShouldMatchers{
  import lists.ListOperationsFlatMap._

  " We want to implement function filter and result " should " be list without elements that match with function " in new ListNotEmpty {
    val expected = MyList(1, 3)
    list.filter((a) => a == 2) shouldBe expected
  }

  it should " be empty list if match all elements " in new ListNotEmpty {
    val expected = MyList()
    list.filter((a) => a < 4) shouldBe expected
  }
}
