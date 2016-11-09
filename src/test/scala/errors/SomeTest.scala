package errors

import org.scalatest.FlatSpec
import org.scalatest.ShouldMatchers

class SomeTest extends FlatSpec with ShouldMatchers{

  " We want to implement map in Some and result " should " be new Some that apply function in elements  " in {
    val value = 3
    val option = Some(2)
    option.map((a)=>value) shouldBe Some(value)
  }

  " We want to implement flatMap in Some and result " should " be new Some when not deep " in {
    val value = 3
    val option = Some(2)
    option.flatMap((a)=>Some(value)) shouldBe Some(value)
  }

  it should " be new Some without deep when exist deep " in {
    val value = 3
    val option = Some(Some(2))
    option.flatMap((a)=>Some(value)) shouldBe Some(value)
  }

  " We want to implement getOrElse in Some and result " should " be value of get " in {
    val value = 3
    val option = Some(value)
    option.getOrElse("assasasa") shouldBe  value
  }
}
