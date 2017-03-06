package ramdoms

import org.scalatest.FlatSpec
import org.scalatest.ShouldMatchers

class RandomGeneratorTest extends FlatSpec with ShouldMatchers{

  object MockGenerator extends RandomGenerator {

    override def nextInt: (Int, RandomGenerator) = ???
  }


  "We want to generate Random number and result " should " be Zero when nextValue return MinValue" in new RandomGenerator {
    override def nextInt: (Int, RandomGenerator) = (Int.MinValue,MockGenerator)
    nonNegativeInt shouldBe (0,MockGenerator)
  }

  it should " be zero + |int.minValue| when nextValue return zero " in new RandomGenerator {
    override def nextInt: (Int, RandomGenerator) = (0,MockGenerator)
    nonNegativeInt shouldBe (-1*Int.MinValue,MockGenerator)
  }

  it should " be zero when result sum |Int.MinValue| is Int.MaxValue sum one" in new RandomGenerator {
     val x = Int.MaxValue + 1 - (-1*Int.MinValue)
    override def nextInt: (Int, RandomGenerator) = (x,MockGenerator)
    nonNegativeInt shouldBe (0 ,MockGenerator)
  }
}
