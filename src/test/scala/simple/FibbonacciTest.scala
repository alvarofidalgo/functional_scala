package simple

import org.scalatest.FlatSpec
import org.scalatest.ShouldMatchers

class FibbonacciTest extends FlatSpec with ShouldMatchers{



  "We want to calculate fibbonnacci numbers and result " should " be cero if n = 0 " in {
      val expected = 0
      val position = 0
      Fibbonacci.fib(position) shouldBe expected
  }

  it should " be one if position is first " in {
    val expected = 1
    val position = 1
    Fibbonacci.fib(position) shouldBe expected
  }

  it should " be one if position is two " in {
    val expected = 1
    val position = 2
    Fibbonacci.fib(position) shouldBe expected
  }

  it should " be two if position is three " in {
    val expected = 2
    val position = 3
    Fibbonacci.fib(position) shouldBe expected
  }

  it should " be three if position is four " in {
    val expected = 3
    val position = 4
    Fibbonacci.fib(position) shouldBe expected
  }

  it should " be five if position is five " in {
    val expected = 5
    val position = 5
    Fibbonacci.fib(position) shouldBe expected
  }

  it should " be eigth if position is six " in {
    val expected = 8
    val position = 6
    Fibbonacci.fib(position) shouldBe expected
  }
}
