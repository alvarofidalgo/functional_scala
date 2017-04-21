package ramdoms

import doubles.{DoubleRandomized, MockGenerator}
import org.scalatest.{FlatSpec, ShouldMatchers}
import types.MyTypes.{StateDouble, StateStringFlatMap}
import types.StateTypes._


//TODO : Delete DRY
class RandomGenerator2StateTest extends FlatSpec with ShouldMatchers{

  import ramdoms.RandomGeneratorState._

  trait Map2Function {
    val f:(String,Int)=>Double = (a,b) => (a.length + b).toDouble
    val entry : RandomState[String] = (RandomGenerator)=> ("AB",MockGenerator)
  }

  " We want to implement map function with State and result " should " be A transform in 1 " in  {
      val entry : RandomState[String] = (RandomGenerator)=> ("A",MockGenerator)
      val result: RandomState[Int] = (RandomGenerator)=> (1,MockGenerator)
      entry.map(string=>string.length)(MockGenerator) shouldBe result(MockGenerator)

  }


  it should " be AB transform in 2 " in {
    val entry : RandomState[String] = (RandomGenerator)=> ("AB",MockGenerator)
    val result: RandomState[Int] = (RandomGenerator)=> (2,MockGenerator)
    entry.map(string=>string.length)(MockGenerator) shouldBe result(MockGenerator)
  }


  " We want to implement map2 function and result " should " be  AB and 1 transform in 3.0 " in new Map2Function{

    val secondEntry: RandomState[Int] = (RandomGenerator)=> (1,MockGenerator)
    val result:RandomState[Double] = (RandomGenerator)=> (3.0,MockGenerator)
    entry.map2(secondEntry)(f)(MockGenerator) shouldBe result(MockGenerator)
  }

  it should " be AB and 2 transform in 4.0 " in new Map2Function{
    val secondEntry: RandomState[Int] = (RandomGenerator)=> (2,MockGenerator)
    val result:RandomState[Double] = (RandomGenerator)=> (4.0,MockGenerator)
    entry.map2(secondEntry)(f)(MockGenerator) shouldBe result(MockGenerator)
  }


  " We want to implement flatmap function and result " should " be (RandomGenerator) => (2,MockGenerator) when entry is (RandomGenerator)=> (\"AB\",MockGenerator)" in {
    val entry : RandomState[String] = (RandomGenerator)=> ("AB",MockGenerator)
    val f:String=>RandomState[Int] = (string) => (RandomGenerator)=>(string.length,MockGenerator)
    val result:RandomState[Int] = (RandomGenerator)=> (2,MockGenerator)
    entry.flatMap(f)(MockGenerator)  shouldBe result(MockGenerator)
  }

  it should " be (RandomGenerator) => (3,MockGenerator) when entry is (RandomGenerator)=> (\"ABC\",MockGenerator) " in {
    val entry : RandomState[String] = (RandomGenerator)=> ("ABC",MockGenerator)
    val f:String=>RandomState[Int] = (string) => (RandomGenerator)=>(string.length,MockGenerator)
    val result:RandomState[Int] = (RandomGenerator)=> (3,MockGenerator)
    entry.flatMap(f)(MockGenerator)  shouldBe result(MockGenerator)
  }

  trait Constants {
    val minValue = -3
    val maxValue = 3

    def check(res:Double,entry:Int) = {
      val result:RandomState[Double]= (doubleValue) => (res,MockGenerator)
      val parameter = DoubleRandomized(min = minValue,max=maxValue,next = entry)
      val generator:RandomState[Int] = (RandomGenerator)=>(minValue,RandomGenerator)
      generator.toDoubleRand(parameter)(MockGenerator) shouldBe result(MockGenerator)
    }

  }


  " We want to implement function that return Double between 0 and 1 and result " should " be zero when return min Value " in new Constants{
    check(0,minValue)

  }

  it should " be 1/6 when return min + 1" in new Constants {
    check(1.toDouble/6.toDouble,minValue + 1)
  }

  it should " be 2/6 when return min +2 " in new Constants {
    check(2.toDouble/6.toDouble,minValue + 2)
  }

  it should " be zero when return min + 6 " in new Constants {
    check(0.toDouble/6.toDouble,minValue + 6)
  }

  it should " be 1/6 when return min + 7 " in new Constants {
    check(1.toDouble/6.toDouble,minValue + 7)

  }

}
