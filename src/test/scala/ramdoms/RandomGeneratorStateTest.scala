package ramdoms

import org.scalatest.{FlatSpec, ShouldMatchers}
import types.StateTypes.RandomState
import doubles.{DoubleRandomized, MockGenerator}
import types.MyTypes.{CustomDouble, StateDouble}

class RandomGeneratorStateTest extends FlatSpec with ShouldMatchers{


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
    val result:(StateDouble,RandomGenerator) = (StateDouble(0.toDouble),MockGenerator)
    result.check(minValue)
  }

  it should " be 1/6 when return min + 1" in new Constants {
    val result:(StateDouble,RandomGenerator) = (StateDouble(1.toDouble/6.toDouble),MockGenerator)
    result.check(minValue + 1)
  }

  it should " be 2/6 when return min +2 " in new Constants {
    val result:(StateDouble,RandomGenerator) = (StateDouble(2.toDouble/6.toDouble),MockGenerator)
    result.check(minValue +2)
  }

  it should " be zero when return min + 6 " in new Constants {
    val result:(StateDouble,RandomGenerator) = (StateDouble(0.toDouble/6.toDouble),MockGenerator)
    result.check(minValue + 6)
  }

  it should " be 1/6 when return min + 7 " in new Constants {
    val result:(StateDouble,RandomGenerator) = (StateDouble(1.toDouble/6.toDouble),MockGenerator)
    result.check(minValue + 7)
  }


  it should " be zero when return min + 12 " in new Constants {
    val result:(StateDouble,RandomGenerator) = (StateDouble(0.toDouble/6.toDouble),MockGenerator)
    result.check(minValue + 12)
  }


  " We want to implement map2 function and result  " should " be new ramdom combine by function " in {
    val first:RandomState[Int] = null
    val second:RandomState[Double] = null
    val expected : RandomState[String] = null
    val f:(Int,Double) => String = null
    first.map2(second)(f)
  }

}
