package ramdoms

import org.scalatest.{FlatSpec, ShouldMatchers}
import doubles.MockGenerator
import types.MyTypes.StateStringFlatMap
import types.MyTypes.{StateDouble, StateStringMap, StateStringMap2}

class RandomGeneratorStateTest extends FlatSpec with ShouldMatchers{


  trait Constants {
    val minValue = -3
    val maxValue = 3

  }
  
  import checkers.CheckerState.syntax._

  " We want to implement Map function and result " should " be (A,Rng1) when we have (1,Rng1)" in {
    val res:(StateStringMap,RandomGenerator) = (StateStringMap("A"),MockGenerator)
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


  " We want to implement map2 function and result  " should " be new ramdom combine by function " in new Constants {

    val result:(StateStringMap2,RandomGenerator)  = (StateStringMap2("1+2.0"),MockGenerator)
    result.check(0)

  }


  "We want to implement flatMap function and result " should " be equals function result with first parameter " in {

    val result:(StateStringFlatMap,RandomGenerator) = (StateStringFlatMap("is-1"),MockGenerator)
    result.check(0)

  }

}
