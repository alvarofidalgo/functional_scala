package checkers

import doubles.DoubleRandomized
import org.scalatest.ShouldMatchers
import ramdoms.RandomGenerator


trait Checker[A]  extends ShouldMatchers{

  val minValue = -3
  val maxValue = 3


  def functionToExecute:(Int)=>A

  def test(addMinValue:Int,res:A) = {
    functionToExecute(addMinValue) shouldBe res
  }

}

trait CheckerInstance {

  def apply[A](implicit checker: Checker[A]): Checker[A] = checker

  implicit val nonNegative = new Checker[(Int,RandomGenerator)] {
    override def functionToExecute: (Int) => (Int, RandomGenerator) =
                       (addMin) =>
                         DoubleRandomized(min = minValue,
                                          max=maxValue,
                                          next = minValue + addMin).nonNegativeInt
  }

  implicit val doubleRandom = new Checker[(Double,RandomGenerator)] {
    override def functionToExecute: (Int) => (Double, RandomGenerator) =
                             (addMin) => {
                               val result =
                                 DoubleRandomized(min = minValue,
                                                  max=maxValue,
                                                  next = minValue + addMin).doubleRandom
                               (result._1,result._2)
                             }
  }

  implicit val intDoubleRandom = new Checker[((Int,Double),RandomGenerator)] {
    override def functionToExecute: (Int) => ((Int, Double), RandomGenerator) =
                  (addMin) =>
                    DoubleRandomized(min = minValue,
                                     max=maxValue,
                                     next = minValue + addMin).intDoubleRandom
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
