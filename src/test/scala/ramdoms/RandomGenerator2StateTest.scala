package ramdoms

import doubles.MockGenerator
import org.scalatest.{FlatSpec, ShouldMatchers}
import types.StateTypes._


//TODO : when remake this test then change Name
class RandomGenerator2StateTest extends FlatSpec with ShouldMatchers{

  import ramdoms.RandomGeneratorState._


  trait MapFunction {

      val f:(String)=>Int = (a)=> a match {
         case "A"=>1
         case "B"=>2
       }
  }

  " We want to implement map function with State and result " should " be A transform in 1 " in new MapFunction {

      val entry : RandomState[String] = (RandomGenerator)=> ("A",MockGenerator)
      val result: RandomState[Int] = (RandomGenerator)=> (1,MockGenerator)
       entry.map(f)(MockGenerator) shouldBe result(MockGenerator)

  }


  it should " be B transform in two " in new MapFunction {
    val entry : RandomState[String] = (RandomGenerator)=> ("B",MockGenerator)
    val result: RandomState[Int] = (RandomGenerator)=> (2,MockGenerator)
    entry.map(f)(MockGenerator) shouldBe result(MockGenerator)
  }

}
