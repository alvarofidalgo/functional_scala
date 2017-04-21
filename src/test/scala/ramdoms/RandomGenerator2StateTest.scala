package ramdoms

import doubles.MockGenerator
import org.scalatest.{FlatSpec, ShouldMatchers}
import types.StateTypes._


//TODO : REFACTOR TIME
class RandomGenerator2StateTest extends FlatSpec with ShouldMatchers{

  import ramdoms.RandomGeneratorState._

  trait Map2Function {
    val f:(String,Int)=>Double = (a,b) => (a.length + b).toDouble
    val entry : RandomState[String] = (RandomGenerator)=> ("AB",MockGenerator)

    def check (secondEntry:RandomState[Int],result:RandomState[Double]) =
      entry.map2(secondEntry)(f)(MockGenerator) shouldBe result(MockGenerator)
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
    check(secondEntry,result)
  }

  it should " be AB and 2 transform in 4.0 " in new Map2Function{
    val secondEntry: RandomState[Int] = (RandomGenerator)=> (2,MockGenerator)
    val result:RandomState[Double] = (RandomGenerator)=> (4.0,MockGenerator)
    check(secondEntry,result)
  }

}
