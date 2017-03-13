package ramdoms

import doubles.MockGenerator
import org.junit.runner.RunWith
import org.scalatest.FlatSpec
import org.scalatest.ShouldMatchers
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class RandomGeneratorTest extends FlatSpec with ShouldMatchers{

  import checkers.Checker.syntax._

  "We want to generate Random number and result " should " be Zero when nextValue return MinValue" in   {
    val result:(Int,RandomGenerator) = (0,MockGenerator)
    result.check(addMinValue = 0)

  }


  it should " be one when NextInt return next MinValue " in   {
    val result:(Int,RandomGenerator) = (1,MockGenerator)
    result.check(addMinValue = 1)
  }

  it should " be two when NextInt return next two minValue " in    {
    val result:(Int,RandomGenerator) = (2,MockGenerator)
    result.check(addMinValue = 2)
  }

  it should "be max when return min sum nexValue is  max " in   {
    val result:(Int,RandomGenerator) = (3,MockGenerator)
    result.check(addMinValue = 3)
  }

  it should "be zero when return min sum nexValue is one upper than max " in    {
    val result:(Int,RandomGenerator) = (0,MockGenerator)
    result.check(addMinValue = 4)
  }

  it should "be one when return min sum nexValue is two upper than max " in  {
    val result:(Int,RandomGenerator) = (1,MockGenerator)
    result.check(addMinValue = 5)
  }


  " We want to implement function that return Double between 0 and 1 and result " should " be zero when return min Value " in  {
    val result:(Double,RandomGenerator) = (0.toDouble,MockGenerator)
    result.check(addMinValue = 0)
  }

  it should " be 1/6 when return min + 1"  in {
    val result:(Double,RandomGenerator) = (1.toDouble/6.toDouble,MockGenerator)
    result.check(addMinValue = 1)
  }

  it should " be 2/6 when return min +2 " in  {
    val result:(Double,RandomGenerator) = (2.toDouble/6.toDouble,MockGenerator)
    result.check(addMinValue = 2)
  }

  it should " be zero when return min + 6 " in {
    val result:(Double,RandomGenerator) = (0.toDouble,MockGenerator)
    result.check(addMinValue = 6)
  }

  it should " be 1/6 when return min + 7 " in  {
    val result:(Double,RandomGenerator) = (1.toDouble/6.toDouble,MockGenerator)
    result.check(addMinValue = 7)
  }

  it should " be zero when return min + 12 " in  {
    val result:(Double,RandomGenerator) = (0.toDouble,MockGenerator)
    result.check(addMinValue = 12)
  }


  " We want to implement function that return int double and result " should " be (0,0) when nexInt is Min" in {
    val result:((Int,Double),RandomGenerator)= ((0,0.toDouble),MockGenerator)
    result.check(addMinValue = 0)
  }

  it should " be (1,1/6) when nextInt is Min + 1"  in {
    val result:((Int,Double),RandomGenerator) = ((1,1.toDouble/6.toDouble),MockGenerator)
    result.check(addMinValue = 1)
  }

  it should " be (2,0) when NextInt is Min + 6" in {
    val result:((Int,Double),RandomGenerator) = ((2,0.toDouble),MockGenerator)
    result.check(addMinValue = 6)
  }

}
