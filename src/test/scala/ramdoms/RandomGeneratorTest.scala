package ramdoms

import org.junit.runner.RunWith
import org.scalatest.FlatSpec
import org.scalatest.ShouldMatchers
import org.scalatest.junit.JUnitRunner
import doubles.{DoubleRandomized, MockGenerator}

@RunWith(classOf[JUnitRunner])
class RandomGeneratorTest extends FlatSpec with ShouldMatchers{

  trait Constants {
     val minValue = -3
     val maxValue = 3

     def buildRandomize(addToMinValue:Int):RandomGenerator = {
       DoubleRandomized(min = minValue,max=maxValue,next = minValue + addToMinValue)
     }
  }


  trait Checker[A] extends Constants{


    def functionToExecute:(Int)=>(A,RandomGenerator)


    def test(addMinValue:Int,result:A) = {
      functionToExecute(addMinValue) shouldBe (result,MockGenerator)
    }

  }

  trait CheckerInstance {

    def apply[A](implicit checker: Checker[A]): Checker[A] = checker

    implicit val nonNegative = new Checker[Int] {
      override def functionToExecute: (Int) => (Int, RandomGenerator) =
                        (a) => buildRandomize(addToMinValue=a).nonNegativeInt
    }

    implicit val doubleRandom = new Checker[Double] {
      override def functionToExecute: (Int) => (Double, RandomGenerator) =
                       (a) => buildRandomize(addToMinValue=a).doubleRandom
    }


    implicit val intDoubleRandom = new Checker[(Int,Double)] {
      override def functionToExecute: (Int) => ((Int, Double), RandomGenerator) = (a)=> buildRandomize(addToMinValue=a).intDoubleRandom
    }

  }

  trait CheckerSyntax {

    object syntax {

        implicit class Check[A] (result:A)(implicit checker: Checker[A]){

             def check(addMinValue:Int) = checker.test(addMinValue,result)

        }
    }
  }

  object Checker extends  CheckerInstance with CheckerSyntax

  import Checker.syntax._

  "We want to generate Random number and result " should " be Zero when nextValue return MinValue" in   {
    val result:Int = 0
    result.check(addMinValue = 0)

  }


  it should " be one when NextInt return next MinValue " in   {
    val result:Int = 1
    result.check(addMinValue = 1)
  }

  it should " be two when NextInt return next two minValue " in    {
    val result:Int = 2
    result.check(addMinValue = 2)
  }

  it should "be max when return min sum nexValue is  max " in   {
    val result:Int = 3
    result.check(addMinValue = 3)
  }

  it should "be zero when return min sum nexValue is one upper than max " in    {
    val result:Int = 0
    result.check(addMinValue = 4)
  }

  it should "be one when return min sum nexValue is two upper than max " in  {
    val result:Int = 1
    result.check(addMinValue = 5)
  }


  " We want to implement function that return Double between 0 and 1 and result " should " be zero when return min Value " in  {
    val result:Double = 0
    result.check(addMinValue = 0)
  }

  it should " be 1/6 when return min + 1"  in {
    val result:Double = 1/6
    result.check(addMinValue = 1)
  }

  it should " be 2/6 when return min +2 " in  {
    val result:Double = 2/6
    result.check(addMinValue = 2)
  }

  it should " be zero when return min + 6 " in {
    val result:Double = 0
    result.check(addMinValue = 6)
  }

  it should " be 1/6 when return min + 7 " in  {
    val result:Double = 1/6
    result.check(addMinValue = 7)
  }

  it should " be zero when return min + 12 " in  {
    val result:Double = 0
    result.check(addMinValue = 12)
  }


  " We want to implement function that return int double and result " should " be (0,0) when nexInt is Min" in {
    val result:(Int,Double)= (0,0)
    result.check(addMinValue = 0)
  }

  it should " be (1,1/6) when nextInt is Min + 1"  in {
    val result:(Int,Double) = (1,1/6)
    result.check(addMinValue = 1)
  }

  it should " be (2,0) when NextInt is Min + 6" in {
    val result:(Int,Double) = (2,0)
    result.check(addMinValue = 6)
  }

}
