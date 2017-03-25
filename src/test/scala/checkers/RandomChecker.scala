package checkers

import doubles.{DoubleRandomized, MockGenerator}
import org.scalatest.ShouldMatchers
import ramdoms.RandomGenerator
import types.MyTypes.{CustomDouble, StateDouble, StateStringMap}
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

  import ramdoms.RandomGeneratorState._

  def apply[A](implicit checker: Checker[A]): Checker[A] = checker

  implicit val nonNegative = new Checker[(Int,RandomGenerator)] {
    override def functionToExecute: (Int) => (Int, RandomGenerator) =
                       (a) => buildRandomize(addToMinValue=a).nonNegativeInt
  }

  implicit val doubleRandom = new Checker[(CustomDouble,RandomGenerator)] {
    override def functionToExecute: (Int) => (CustomDouble, RandomGenerator) =
                             (a) => {
                               val result = buildRandomize(addToMinValue=a).doubleRandom
                               (CustomDouble(result._1),result._2)
                             }
  }

  implicit val intDoubleRandom = new Checker[((Int,Double),RandomGenerator)] {
    override def functionToExecute: (Int) => ((Int, Double), RandomGenerator) =
                                  (a)=> buildRandomize(addToMinValue=a).intDoubleRandom
  }

  implicit val randomMap = new Checker[(StateStringMap,RandomGenerator)] {
    override def functionToExecute: (Int) => (StateStringMap, RandomGenerator) = {
      {
        (addMin)=> {
          val generator: RandomState[Int] = (RandomGenerator) => (1, MockGenerator)
          generator.map ((_) =>StateStringMap( "A")) (MockGenerator)
        }
      }
    }
  }


  implicit val doubleState = new Checker[(StateDouble,RandomGenerator)] {

    override def functionToExecute: (Int) => (StateDouble, RandomGenerator) = {

      (addMin)=> {
        val ramdomize = DoubleRandomized(min = minValue,max=maxValue,next = addMin)
        val generator:RandomState[Int] = (RandomGenerator)=>(addMin,RandomGenerator)
        val result =generator.toDoubleRand(ramdomize)(MockGenerator)
        (StateDouble(result._1),result._2)
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
