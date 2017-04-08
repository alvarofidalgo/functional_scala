package ramdoms

import doubles.MockGenerator
import org.scalatest.{FlatSpec, ShouldMatchers}
import types.StateTypes._


//TODO : when remake this test then change Name
class RandomGenerator2StateTest extends FlatSpec with ShouldMatchers{

  import ramdoms.RandomGeneratorState._

  " We want to implement map function with State and result " should " be A transform in 1 " in {

      val entry : RandomState[String] = (RandomGenerator)=> ("A",MockGenerator)
      val result: RandomState[Int] = (RandomGenerator)=> (1,MockGenerator)
      val f:(String)=>Int = (a)=>1
       entry.map(f)(MockGenerator) shouldBe result(MockGenerator)

  }

}
