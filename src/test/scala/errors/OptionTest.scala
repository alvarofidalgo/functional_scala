package errors

import org.scalatest.FlatSpec
import org.scalatest.ShouldMatchers

class OptionTest extends FlatSpec with ShouldMatchers{


  " We want to implement map function and result " should " be type Some when is not None " in {
    val value = 3
    val option = Option(value)
    option.map((a)=>value) shouldBe Some(value)
  }

  it should " be new Some with result executed function each element " in {

    val option = Option()
    option.map((a)=>3) shouldBe Some(3)
  }

  it should "None when whe have option None " in {
    val option = Option(null)
    option.map((a)=>3) shouldBe None
  }

  " We want to implement flatMap in Some and result " should " be new Some when not deep " in {
    val value = 3
    val option = Option(2)
    option.flatMap((a)=>Some(value)) shouldBe Some(value)
  }

  it should " be execute function over each deep element" in {
    val value = 3
    val option = Option(Option(2))
    option.flatMap((a)=>Some(value)) shouldBe Some(value)
  }

  it should "None when get is null " in {
    val option = Option(null)
    option.flatMap((a)=>Some(value)) shouldBe None
  }

  " We want to implement getOrElse in Option and result " should " be value of get " in {
    val value = 3
    val option = Option(value)
    option.getOrElse("assasasa") shouldBe  value
  }

  it should " default value when get is null " in {
    val default ="aaaaaaaa"
    val option = Option(null)
    option.getOrElse(default) shouldBe  default
  }

  " We want to implement orElse in Option and result " should " be Option if value not null " in {
    val value = 3
    val option = Option(value)
    option.orElse(Option(12)) shouldBe  Some(value)
  }

  it should " be default value if Option is null " in {
    val value = 3
    val option = Option(null)
    option.orElse(Option(value)) shouldBe Option(value)
  }
}

