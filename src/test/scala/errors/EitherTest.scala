package errors

import org.scalatest.FlatSpec
import org.scalatest.ShouldMatchers

class EitherTest extends FlatSpec with ShouldMatchers {

  " We want to implement map function in Either and result " should " be new Either that apply function " in {

     val either = Left("This is error")
     either.map((a)=>a.length) shouldBe Left("This is error".length)

  }

}

