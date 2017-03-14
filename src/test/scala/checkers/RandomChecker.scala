package checkers

import doubles.{DoubleRandomized, MockGenerator}
import org.scalatest.ShouldMatchers
import ramdoms.RandomGenerator
import types.StateTypes._


trait Checker[A]  extends ShouldMatchers{

  val minValue = -3
  val maxValue = 3

  def buildRandomize(addToMinValue:Int):RandomGenerator = {
    DoubleRandomized(min = minValue,max=maxValue,next = minValue + addToMinValue)
  }


  def functionToExecute:(Int)=>A

  def test(addMinValue:Int,res:A) = {
    functionToExecute(addMinValue) shouldBe res
  }

}

trait CheckerInstance {

  def apply[A](implicit checker: Checker[A]): Checker[A] = checker

  implicit val nonNegative = new Checker[(Int,RandomGenerator)] {
    override def functionToExecute: (Int) => (Int, RandomGenerator) =
                       (a) => buildRandomize(addToMinValue=a).nonNegativeInt
  }

  implicit val doubleRandom = new Checker[(Double,RandomGenerator)] {
    override def functionToExecute: (Int) => (Double, RandomGenerator) =
                             (a) => buildRandomize(addToMinValue=a).doubleRandom
  }


  implicit val intDoubleRandom = new Checker[((Int,Double),RandomGenerator)] {
    override def functionToExecute: (Int) => ((Int, Double), RandomGenerator) =
                                  (a)=> buildRandomize(addToMinValue=a).intDoubleRandom
  }

  implicit val randomMap = new Checker[(String,RandomGenerator)] {
    import ramdoms.RandomGeneratorState._
    override def functionToExecute: (Int) => (String, RandomGenerator) = {
      {
        (addMin)=> {
          val generator: RandomState[Int] = (RandomGenerator) => (1, MockGenerator)
          generator.map ((_) => "A") (MockGenerator)
        }
      }
    }
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
