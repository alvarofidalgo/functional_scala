package errors

import org.scalatest.FlatSpec
import org.scalatest.ShouldMatchers

class SomeTest extends FlatSpec with ShouldMatchers{

  " We want to implement map in MyOption and result " should " be None if not have elments " in {
    val none = Some(None)
    val value = 3
    none.map((a)=>value) shouldBe Some(value)
  }

  it should " be new Option that apply function in elements " in {
    val some = Some(3)
    some.map((a)=>s"$a") shouldBe Some("3")
  }
}
