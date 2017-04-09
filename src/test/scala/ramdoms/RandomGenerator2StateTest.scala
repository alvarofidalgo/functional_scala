package ramdoms

import doubles.MockGenerator
import org.scalatest.{FlatSpec, ShouldMatchers}
import types.StateTypes._


//TODO : when remake this test then change Name
class RandomGenerator2StateTest extends FlatSpec with ShouldMatchers{

  import ramdoms.RandomGeneratorState._


  trait MapFunction {

      val f:(String)=>Int = {
        case "A" => 1
        case "B" => 2
      }

      def check(entry:RandomState[String],result:RandomState[Int])=
           entry.map(f)(MockGenerator) shouldBe result(MockGenerator)
  }

  " We want to implement map function with State and result " should " be A transform in 1 " in new MapFunction {

      val entry : RandomState[String] = (RandomGenerator)=> ("A",MockGenerator)
      val result: RandomState[Int] = (RandomGenerator)=> (1,MockGenerator)
       check(entry,result)

  }


  it should " be B transform in 2 " in new MapFunction {
    val entry : RandomState[String] = (RandomGenerator)=> ("B",MockGenerator)
    val result: RandomState[Int] = (RandomGenerator)=> (2,MockGenerator)
    check(entry,result)
  }

}
