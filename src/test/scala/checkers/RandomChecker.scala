package checkers

import doubles.{DoubleRandomized, MockGenerator}
import org.scalatest.ShouldMatchers
import ramdoms.RandomGenerator
import types.MyTypes._
import types.StateTypes._


trait Checker[A]  extends ShouldMatchers{

  val minValue = -3
  val maxValue = 3

  def buildRandomize(addToMinValue:Int):RandomGenerator = {
    DoubleRandomized(min = minValue,max=maxValue,next = addToMinValue)
  }


  def functionToExecute:(Int)=>A

  def test(addMinValue:Int,res:A) = {
    functionToExecute(addMinValue) shouldBe res
  }

}

trait CheckerInstance {

  import ramdoms.RandomGeneratorState._

  def apply[A](implicit checker: Checker[A]): Checker[A] = checker

  implicit val nonNegative = new Checker[(Int,RandomGenerator)] {
    override def functionToExecute: (Int) => (Int, RandomGenerator) =
                       (addMin) => buildRandomize(addToMinValue=minValue + addMin).nonNegativeInt
  }

  implicit val doubleRandom = new Checker[(CustomDouble,RandomGenerator)] {
    override def functionToExecute: (Int) => (CustomDouble, RandomGenerator) =
                             (addMin) => {
                               val result = buildRandomize(addToMinValue=minValue + addMin).doubleRandom
                               (CustomDouble(result._1),result._2)
                             }
  }

  implicit val intDoubleRandom = new Checker[((Int,Double),RandomGenerator)] {
    override def functionToExecute: (Int) => ((Int, Double), RandomGenerator) =
                                  (addMin)=> buildRandomize(addToMinValue=minValue + addMin).intDoubleRandom
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
