package errors

import org.scalatest.FlatSpec
import org.scalatest.ShouldMatchers

class EitherTest extends FlatSpec with ShouldMatchers {

  " We want to implement map function in Either and result " should " be new Either with same side value in Rigth " in {
    val message = " This is no error"
    val right = Right(message)
    right.map((a)=>a.length) shouldBe Right(message.length)
  }


  it should " nothing when side is left " in {
    val message = " This is no error"
    val left = Left(message)
    left.map((a)=>a) shouldBe Left(message)
  }
}

