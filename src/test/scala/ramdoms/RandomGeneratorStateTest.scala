package ramdoms

import org.scalatest.{FlatSpec, ShouldMatchers}
import types.StateTypes.RandomState
import doubles.MockGenerator




class RandomGeneratorStateTest extends FlatSpec with ShouldMatchers{

  import ramdoms.RandomGeneratorState._
  " We want to implement Map function and result " should " be (A,Rng1) when we have (1,Rng1)" in {
      val generator:RandomState[Int] = (MockGenerator)=>(1,MockGenerator)
      val expected:RandomState[String]  =  (MockGenerator)=>("A",MockGenerator)
      val result =  generator.map((_)=>"A")
      result(MockGenerator) shouldBe expected(MockGenerator)
  }

}
