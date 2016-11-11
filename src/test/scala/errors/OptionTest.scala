package errors

import org.scalatest.FlatSpec
import org.scalatest.ShouldMatchers

class OptionTest extends FlatSpec with ShouldMatchers{


  " We want to implement map function and result " should " be type Some when is not None " in {
    val value = 3
    val option = Option(value)
    option.map((a)=>value) shouldBe Some(value)
  }

  it should " be None when no have elements " in {

    val option = Option()
    option.map((a)=>3) shouldBe Some(3)
  }

  it should "None wheen whe have option None " in {
    val option = Option(null)
    option.map((a)=>3) shouldBe None
  }
}
