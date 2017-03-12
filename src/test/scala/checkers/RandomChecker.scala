package checkers

import doubles.{DoubleRandomized, MockGenerator}
import org.scalatest.ShouldMatchers
import ramdoms.RandomGenerator


trait Checker[A]  extends ShouldMatchers{

  val minValue = -3
  val maxValue = 3

  def buildRandomize(addToMinValue:Int):RandomGenerator = {
    DoubleRandomized(min = minValue,max=maxValue,next = minValue + addToMinValue)
  }


  def functionToExecute:(Int)=>(A,RandomGenerator)

  def result:(A)=>(A,RandomGenerator)


  def test(addMinValue:Int,res:A) = {
    functionToExecute(addMinValue) shouldBe result(res)
  }

}

trait CheckerInstance {

  def apply[A](implicit checker: Checker[A]): Checker[A] = checker

  implicit val nonNegative = new Checker[Int] {
    override def functionToExecute: (Int) => (Int, RandomGenerator) =
                       (a) => buildRandomize(addToMinValue=a).nonNegativeInt
    override def result:(Int)=>(Int,RandomGenerator) = (a)=> (a,MockGenerator)

  }

  implicit val doubleRandom = new Checker[Double] {
    override def functionToExecute: (Int) => (Double, RandomGenerator) =
                             (a) => buildRandomize(addToMinValue=a).doubleRandom

    override def result: (Double) => (Double, RandomGenerator) = (a)=> (a,MockGenerator)
  }


  implicit val intDoubleRandom = new Checker[(Int,Double)] {
    override def functionToExecute: (Int) => ((Int, Double), RandomGenerator) =
                                  (a)=> buildRandomize(addToMinValue=a).intDoubleRandom

    override def result: ((Int, Double)) => ((Int, Double), RandomGenerator) = (a)=>(a,MockGenerator)
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
