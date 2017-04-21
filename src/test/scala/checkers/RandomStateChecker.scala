package checkers

import doubles.{DoubleRandomized, MockGenerator}
import org.scalatest._
import ramdoms.RandomGenerator
import types.MyTypes._
import types.StateTypes._


trait CheckerState[A,B]  extends ShouldMatchers{

  val minValue = -3
  val maxValue = 3

  def buildRandomize(addToMinValue:Int):RandomGenerator = {
    DoubleRandomized(min = minValue,max=maxValue,next = addToMinValue)
  }


  def functionToExecute:(A)=>B

  def test(addMinValue:A,res:B) = {
    functionToExecute(addMinValue) shouldBe res
  }

}


trait CheckerStateInstance {

  import ramdoms.RandomGeneratorState._

  def apply[A,B](implicit checker: CheckerState[A,B]): CheckerState[A,B] = checker


  implicit val doubleState = new CheckerState[Int,(StateDouble,RandomGenerator)] {

    override def functionToExecute: (Int) => (StateDouble, RandomGenerator) = {

      (addMin)=> {
        val generator:RandomState[Int] = (RandomGenerator)=>(addMin,RandomGenerator)
        val result =generator.toDoubleRand(buildRandomize(addMin))(MockGenerator)
        (StateDouble(result._1),result._2)
      }
    }
  }

  implicit val stringFlatMap = new CheckerState[Int,(StateStringFlatMap,RandomGenerator)] {
    override def functionToExecute: (Int) => (StateStringFlatMap, RandomGenerator) = {
      (addMin)=> {
        val first:RandomState[Int] = (RandomGenerator)=>(1,RandomGenerator)
        val funct:Int=>RandomState[StateStringFlatMap] = {
          (a) =>(RandomGenerator) =>(new StateStringFlatMap(s"is-$a"),RandomGenerator)
        }
        first.flatMap(funct)(MockGenerator)
      }
    }

  }

}


trait CheckerStateSyntax {

  object syntax {

    implicit class CheckState[A,B] (result:B)(implicit checker: CheckerState[A,B]){

      def check(addMinValue:A) = checker.test(addMinValue,result)

    }
  }
}

object CheckerState extends  CheckerStateInstance with CheckerStateSyntax
