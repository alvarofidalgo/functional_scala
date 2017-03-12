package ramdoms

import org.junit.runner.RunWith
import org.scalatest.FlatSpec
import org.scalatest.ShouldMatchers
import org.scalatest.junit.JUnitRunner
import doubles.{DoubleRandomized, MockGenerator, MyRandomized}

@RunWith(classOf[JUnitRunner])
class RandomGeneratorTest extends FlatSpec with ShouldMatchers{

  trait Constants {
     val minValue = -3
     val maxValue = 3

     def buildRandomize(addToMinValue:Int):RandomGenerator = {
       DoubleRandomized(min = minValue,max=maxValue,next = minValue + addToMinValue)
     }
  }

  trait NonNegative extends Constants {

    def testNonNegative(addMinValue:Int,result:Double) =
      buildRandomize(addToMinValue=addMinValue).nonNegativeInt shouldBe (result,MockGenerator)

  }

  trait DoubleRandom  extends Constants {

    def testDoubleRandom(addMinValue:Int,result:Double) =
      buildRandomize(addToMinValue=addMinValue).doubleRandom shouldBe (result,MockGenerator)

  }

  trait  IntDoubleRandom  extends Constants {

    def testIntDoubleRandom (addMinValue:Int,result:(Int,Double)) =
      buildRandomize(addToMinValue=addMinValue).intDoubleRandom shouldBe (result,MockGenerator)
  }


  "We want to generate Random number and result " should " be Zero when nextValue return MinValue" in  new NonNegative {
    testNonNegative(addMinValue = 0,result=0)

  }

  it should " be one when NextInt return next MinValue " in  new NonNegative  {
    testNonNegative(addMinValue = 1,result=1)
  }

  it should " be two when NextInt return next two minValue " in  new NonNegative  {
    testNonNegative(addMinValue = 2,result=2)
  }

  it should "be max when return min sum nexValue is  max " in  new NonNegative  {
    testNonNegative(addMinValue = 3,result=3)
  }

  it should "be zero when return min sum nexValue is one upper than max " in  new NonNegative  {
    testNonNegative(addMinValue = 4,result=0)
  }

  it should "be one when return min sum nexValue is two upper than max " in  new NonNegative  {
    testNonNegative(addMinValue = 5,result=1)
  }

  " We want to implement function that return Double between 0 and 1 and result " should " be zero when return min Value " in  new DoubleRandom {
    testDoubleRandom(addMinValue = 0,result = 0)
  }


  it should " be 1/6 when return min + 1"  in  new DoubleRandom {
    testDoubleRandom(addMinValue = 1,result = 1/6)
  }

  it should " be 2/6 when return min +2 " in  new DoubleRandom {
    testDoubleRandom(addMinValue = 2,result = 2/6)
  }

  it should " be zero when return min + 6 " in  new DoubleRandom {
    testDoubleRandom(addMinValue = 6,result = 0)
  }

  it should " be 1/6 when return min + 7 " in  new DoubleRandom {
    testDoubleRandom(addMinValue = 7,result = 1/6)
  }

  it should " be zero when return min + 12 " in  new DoubleRandom {
    testDoubleRandom(addMinValue = 12,result = 0)
  }

  " We want to implement function that return int double and result " should " be (0,0) when nexInt is Min" in new IntDoubleRandom {
    testIntDoubleRandom (addMinValue = 0,result= (0,0) )
  }

  it should " be (1,1/6) when nextInt is Min + 1"  in new IntDoubleRandom {
    testIntDoubleRandom (addMinValue = 1,result= (1,1/6) )
  }

  it should " be (2,0) when NextInt is Min + 6" in new IntDoubleRandom {
    testIntDoubleRandom (addMinValue = 6,result= (2,0) )
  }


}
