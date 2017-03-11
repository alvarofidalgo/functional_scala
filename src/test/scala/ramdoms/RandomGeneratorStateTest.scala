package ramdoms

import org.scalatest.{FlatSpec, ShouldMatchers}
import types.StateTypes.RandomState
import doubles.MockGenerator


class RandomGeneratorStateTest extends FlatSpec with ShouldMatchers{





  " We want to implement Map function and result " should " be (A,Rng1) when we have (1,Rng1)" in new RandomGeneratorState{
      val generator:RandomState[Int] = (MockGenerator)=>(1,MockGenerator)
      val expected:RandomState[String]  =  (MockGenerator)=>("A",MockGenerator)
      val result =  new RandomGeneratorState().map(generator)((_)=>"A")
      result(MockGenerator) shouldBe expected(MockGenerator)
  }

}
