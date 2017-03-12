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



  "We want to generate Random number and result " should " be Zero when nextValue return MinValue" in  new Checker[Int] {
    override def functionToExecute: (Int) => (Int, RandomGenerator) = (a) => buildRandomize(addToMinValue=a).nonNegativeInt
    test(addMinValue = 0,result=0)

  }

  it should " be one when NextInt return next MinValue " in  new Checker[Int]  {
    override def functionToExecute: (Int) => (Int, RandomGenerator) = (a) => buildRandomize(addToMinValue=a).nonNegativeInt
    test(addMinValue = 1,result=1)


  }

  it should " be two when NextInt return next two minValue " in  new Checker[Int]  {
    override def functionToExecute: (Int) => (Int, RandomGenerator) = (a) => buildRandomize(addToMinValue=a).nonNegativeInt
    test(addMinValue = 2,result=2)
  }

  it should "be max when return min sum nexValue is  max " in  new Checker[Int]  {
    override def functionToExecute: (Int) => (Int, RandomGenerator) = (a) => buildRandomize(addToMinValue=a).nonNegativeInt
    test(addMinValue = 3,result=3)
  }

  it should "be zero when return min sum nexValue is one upper than max " in  new Checker[Int]  {
    override def functionToExecute: (Int) => (Int, RandomGenerator) = (a) => buildRandomize(addToMinValue=a).nonNegativeInt
    test(addMinValue = 4,result=0)
  }

  it should "be one when return min sum nexValue is two upper than max " in  new Checker[Int]  {
    override def functionToExecute: (Int) => (Int, RandomGenerator) = (a) => buildRandomize(addToMinValue=a).nonNegativeInt
    test(addMinValue = 5,result=1)
  }

  " We want to implement function that return Double between 0 and 1 and result " should " be zero when return min Value " in  new Checker[Double] {
    override def functionToExecute: (Int) => (Double, RandomGenerator) =  (a)=>buildRandomize(addToMinValue=a).doubleRandom
    test(addMinValue = 0,result = 0)


  }


  it should " be 1/6 when return min + 1"  in  new Checker[Double] {
    override def functionToExecute: (Int) => (Double, RandomGenerator) =  (a)=>buildRandomize(addToMinValue=a).doubleRandom
    test(addMinValue = 1,result = 1/6)
  }

  it should " be 2/6 when return min +2 " in  new Checker[Double] {
    override def functionToExecute: (Int) => (Double, RandomGenerator) =  (a)=>buildRandomize(addToMinValue=a).doubleRandom
    test(addMinValue = 2,result = 2/6)
  }

  it should " be zero when return min + 6 " in new Checker[Double]{
    override def functionToExecute: (Int) => (Double, RandomGenerator) =  (a)=>buildRandomize(addToMinValue=a).doubleRandom
    test(addMinValue = 6,result = 0)
  }

  it should " be 1/6 when return min + 7 " in  new Checker[Double] {
    override def functionToExecute: (Int) => (Double, RandomGenerator) =  (a)=>buildRandomize(addToMinValue=a).doubleRandom
    test(addMinValue = 7,result = 1/6)
  }

  it should " be zero when return min + 12 " in  new Checker[Double]{
    override def functionToExecute: (Int) => (Double, RandomGenerator) =  (a)=>buildRandomize(addToMinValue=a).doubleRandom
    test(addMinValue = 12,result = 0)
  }

  " We want to implement function that return int double and result " should " be (0,0) when nexInt is Min" in new Checker[(Int,Double)] {
    override def functionToExecute: (Int) => ((Int, Double), RandomGenerator) = (a)=> buildRandomize(addToMinValue=a).intDoubleRandom
    test(addMinValue = 0,result= (0,0) )
  }

  it should " be (1,1/6) when nextInt is Min + 1"  in new Checker[(Int,Double)]  {
    override def functionToExecute: (Int) => ((Int, Double), RandomGenerator) = (a)=> buildRandomize(addToMinValue=a).intDoubleRandom
    test(addMinValue = 1,result= (1,1/6) )
  }

  it should " be (2,0) when NextInt is Min + 6" in new Checker[(Int,Double)] {
    override def functionToExecute: (Int) => ((Int, Double), RandomGenerator) = (a)=> buildRandomize(addToMinValue=a).intDoubleRandom
    test(addMinValue = 6,result= (2,0) )
  }


}
