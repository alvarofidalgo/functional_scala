package ramdoms

import org.scalatest.{FlatSpec, ShouldMatchers}
import types.StateTypes.RandomState
import doubles.DoubleRandomized


class RandomGeneratorStateTest extends FlatSpec with ShouldMatchers{

  import ramdoms.RandomGeneratorState._

  trait Constants {
    val minValue = -3
    val maxValue = 3
    val nextValue = 0
    val randomize = DoubleRandomized(min = minValue,max=maxValue,next = nextValue)
  }

  " We want to implement Map function and result " should " be (A,Rng1) when we have (1,Rng1)" in new Constants{

      val generator:RandomState[Int] = (RandomGenerator)=>(1,RandomGenerator)
      val expected=("A",randomize)
      val result =  generator.map((_)=>"A")
      result(randomize) shouldBe expected
  }


  " We want to implement function that return Double between 0 and 1 and result " should " be zero when return min Value " in new Constants{
    val generator:RandomState[Int] = (RandomGenerator)=>(minValue,RandomGenerator)
    val expected:(Double,RandomGenerator)  = (0,randomize)
    val result:(Double,RandomGenerator) = generator.toDoubleRand(randomize)
    result shouldBe expected
  }

  it should " be 1/6 when return min + 1" in new Constants {
    val generator:RandomState[Int] = (RandomGenerator)=>(minValue+1,RandomGenerator)
    val h:Float=1/6
    val expected:(Double,RandomGenerator) = (h,randomize)
    val result:(Double,RandomGenerator) = generator.toDoubleRand(randomize)
    result._1 shouldBe expected._1
  }

 // it should " be 2/6 when return min +2 "
 // it should " be zero when return min + 6 "
 // it should " be 1/6 when return min + 7 "
 // it should " be zero when return min + 12 "

}
