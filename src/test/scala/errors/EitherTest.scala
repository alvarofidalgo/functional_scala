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


  " We want to implement flatmap function and result " should " be a transformation of right side" in {
    val message = " This is no error"
    val right = Right(message)
    right.flatMap((a)=>Left(a.length)) shouldBe Left(message.length)
  }

  it should " no effects in Left side " in {
    val message = " This is no error"
    val right = Left(message)
    right.flatMap((a)=>a) shouldBe Left(message)

  }

}

