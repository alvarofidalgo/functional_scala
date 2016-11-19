package errors

import org.scalatest.FlatSpec
import org.scalatest.ShouldMatchers



class OptionTest extends FlatSpec with ShouldMatchers{

  trait Value {
    val value = 3
  }

  " We want to implement map function and result " should " be type Some when is not None " in new Value {

    val option = Option(value)
    option.map((a)=>value) shouldBe Some(value)
  }

  it should " be new Some with result executed function each element " in  new Value{

    val option = Option()
    option.map((a)=>value) shouldBe Some(value)
  }

  it should "None when whe have option None " in new Value{
    val option = Option(null)
    option.map((a)=>value) shouldBe None
  }

  " We want to implement flatMap in Some and result " should " be new Some when not deep " in new Value {

    val option = Option(2)
    option.flatMap((a)=>Some(value)) shouldBe Some(value)
  }

  it should " be execute function over each deep element" in new Value {
    val option = Option(Option(2))
    option.flatMap((a)=>Some(value)) shouldBe Some(value)
  }

  it should "None when get is null " in new Value{
    val option = Option(null)
    option.flatMap((a)=>Some(value)) shouldBe None
  }

  " We want to implement getOrElse in Option and result " should " be value of get " in new Value{
    val option = Option(value)
    option.getOrElse("assasasa") shouldBe  value
  }

  it should " default value when get is null " in {
    val default ="aaaaaaaa"
    val option = Option(null)
    option.getOrElse(default) shouldBe  default
  }

  " We want to implement orElse in Option and result " should " be Some if value not null " in new Value{
    val option = Option(value)
    option.orElse(Option(12)) shouldBe  Some(value)
  }

  it should " be default value if Option is null " in new Value{
    val option = Option(null)
    option.orElse(Some(value)) shouldBe Some(value)
  }

  "  We want to implement filter function and result " should " be Some if value satisfy function " in new Value{
      val option = Option(value)
      option.filter((a)=>a==value) shouldBe Some(value)
  }

  it should " be None if value not satisfy function " in new Value {
    val option = Option(value)
    option.filter((a)=>a==12) shouldBe None
  }
}

