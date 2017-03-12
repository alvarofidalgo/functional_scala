package ramdoms

import org.scalatest.{FlatSpec, ShouldMatchers}
import types.StateTypes.RandomState
import doubles.DoubleRandomized


class RandomGeneratorStateTest extends FlatSpec with ShouldMatchers{

  import ramdoms.RandomGeneratorState._
  
  " We want to implement Map function and result " should " be (A,Rng1) when we have (1,Rng1)" in {
      val ramdomized = DoubleRandomized(min = -3,max=3,next = 0)
      val generator:RandomState[Int] = (RandomGenerator)=>(1,RandomGenerator)
      val expected:RandomState[String]  =  (RandomGenerator)=>("A",RandomGenerator)
      val result =  generator.map((_)=>"A")
      result(ramdomized) shouldBe expected(ramdomized)
  }

}
