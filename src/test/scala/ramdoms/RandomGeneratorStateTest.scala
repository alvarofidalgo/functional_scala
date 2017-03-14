package ramdoms

import org.scalatest.{FlatSpec, ShouldMatchers}
import types.StateTypes.RandomState
import doubles.{DoubleRandomized, MockGenerator}

//TODO : This test will need refactor
class RandomGeneratorStateTest extends FlatSpec with ShouldMatchers{

  import ramdoms.RandomGeneratorState._

  trait Constants {
    val minValue = -3
    val maxValue = 3

  }
  
  import checkers.Checker.syntax._

  " We want to implement Map function and result " should " be (A,Rng1) when we have (1,Rng1)" in {
    val res:(String,RandomGenerator) = ("A",MockGenerator)
    val minValue = -3
    res.check(minValue)
  }


  " We want to implement function that return Double between 0 and 1 and result " should " be zero when return min Value " in new Constants{
    val randomize = DoubleRandomized(min = minValue,max=maxValue,next = minValue)
    val generator:RandomState[Int] = (RandomGenerator)=>(minValue,RandomGenerator)
    val expected:(Double,RandomGenerator)  = (0,randomize)
    val result:(Double,RandomGenerator) = generator.toDoubleRand(randomize)(randomize)
    result shouldBe expected
  }

  it should " be 1/6 when return min + 1" in new Constants {
    val randomize = DoubleRandomized(min = minValue,max=maxValue,next =minValue + 1)
    val generator:RandomState[Int] = (RandomGenerator)=>(minValue+1,RandomGenerator)
    val expected:(Double,RandomGenerator) = (1.toDouble/6.toDouble,randomize)
    val result:(Double,RandomGenerator) = generator.toDoubleRand(randomize)(randomize)
    result shouldBe expected
  }

  it should " be 2/6 when return min +2 " in new Constants {
    val randomize = DoubleRandomized(min = minValue,max=maxValue,next =minValue + 2)
    val generator:RandomState[Int] = (RandomGenerator)=>(minValue+1,RandomGenerator)
    val expected:(Double,RandomGenerator) = (2.toDouble/6.toDouble,randomize)
    val result:(Double,RandomGenerator) = generator.toDoubleRand(randomize)(randomize)
    result shouldBe expected
  }

  it should " be zero when return min + 6 " in new Constants {
    val randomize = DoubleRandomized(min = minValue,max=maxValue,next =minValue + 3)
    val generator:RandomState[Int] = (RandomGenerator)=>(minValue+1,RandomGenerator)
    val expected:(Double,RandomGenerator) = (0.toDouble/6.toDouble,randomize)
    val result:(Double,RandomGenerator) = generator.toDoubleRand(randomize)(randomize)
    result shouldBe expected
  }

  it should " be 1/6 when return min + 7 " in new Constants {
    val randomize = DoubleRandomized(min = minValue,max=maxValue,next =minValue + 7)
    val generator:RandomState[Int] = (RandomGenerator)=>(minValue+1,RandomGenerator)
    val expected:(Double,RandomGenerator) = (1.toDouble/6.toDouble,randomize)
    val result:(Double,RandomGenerator) = generator.toDoubleRand(randomize)(randomize)
    result shouldBe expected
  }


  it should " be zero when return min + 12 " in new Constants {
    val randomize = DoubleRandomized(min = minValue,max=maxValue,next =minValue + 12)
    val generator:RandomState[Int] = (RandomGenerator)=>(minValue+1,RandomGenerator)
    val expected:(Double,RandomGenerator) = (0.toDouble/6.toDouble,randomize)
    val result:(Double,RandomGenerator) = generator.toDoubleRand(randomize)(randomize)
    result shouldBe expected
  }

}
