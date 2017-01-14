package errors

import org.scalatest.FlatSpec
import org.scalatest.ShouldMatchers

class EitherTest extends FlatSpec with ShouldMatchers {

  " We want to implement map function in Either and result " should " be new Either with same side value in Rigth " in {
    val message = " This is no error"
    val right = Right(message)
    right.map((a)=>a.length) shouldBe Right(message.length)
  }
}

