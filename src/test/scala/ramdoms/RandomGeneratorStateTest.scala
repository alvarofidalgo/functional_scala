package ramdoms

import org.scalatest.{FlatSpec, ShouldMatchers}
import types.StateTypes.RandomState


class RandomGeneratorStateTest extends FlatSpec with ShouldMatchers{


  trait MyRandomized extends RandomGenerator {
    val min: Int = -3
    val max: Int = 3
    override val limits: (Int, Int) = (min,max)
  }

  object MockGenerator extends MyRandomized {


    override def nextInt: (Int, RandomGenerator) = ???

  }


  " We want to implement Map function and result " should " be (A,Rng1) when we have (1,Rng1)" in new RandomGeneratorState{
      val generator:RandomState[Int] = (MockGenerator)=>(1,MockGenerator)
      val expected:RandomState[String]  =  (MockGenerator)=>("A",MockGenerator)
      val result =  new RandomGeneratorState().map(generator)((a)=>"A")
      result(MockGenerator) shouldBe expected(MockGenerator)
  }

}
