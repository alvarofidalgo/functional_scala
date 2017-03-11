package ramdoms

import org.scalatest.FlatSpec
import org.scalatest.ShouldMatchers

class RandomGeneratorTest extends FlatSpec with ShouldMatchers{


  trait MyRandomized extends RandomGenerator {
    val min = -3
    val max = 3
    override val limits: (Int, Int) = (min,max)
  }

  object MockGenerator extends MyRandomized {


    override def nextInt: (Int, RandomGenerator) = ???

  }


  "We want to generate Random number and result " should " be Zero when nextValue return MinValue" in new MyRandomized {
    override def nextInt: (Int, RandomGenerator) = (min,MockGenerator)
    nonNegativeInt shouldBe (0,MockGenerator)

  }

  it should " be one when NextInt return next MinValue " in new MyRandomized {
    override def nextInt: (Int, RandomGenerator) = (min + 1 ,MockGenerator)
    nonNegativeInt shouldBe (1,MockGenerator)
  }

  it should " be two when NextInt return next two minValue " in new MyRandomized {
    override def nextInt: (Int, RandomGenerator) = (min + 2 ,MockGenerator)
    nonNegativeInt shouldBe (2,MockGenerator)
  }


 /* it should " be two when NextInt return next two MinValue " in new RandomGenerator {
    override def nextInt: (Int, RandomGenerator) = (Int.MinValue + 2 ,MockGenerator)
    nonNegativeInt shouldBe (2,MockGenerator)
  }

  it should " be zero when NextInt return  Int.MaxValue + Int.MinValue  + 1" in new RandomGenerator {
    override def nextInt: (Int, RandomGenerator) = (Int.MaxValue + Int.MinValue + 1 ,MockGenerator)
    nonNegativeInt shouldBe (0,MockGenerator)
  }

  it should "be one when NextInt return   Int.MaxValue + Int.MinValue  + 2 " in new RandomGenerator {
    override def nextInt: (Int, RandomGenerator) = (Int.MaxValue + Int.MinValue + 2 ,MockGenerator)
    nonNegativeInt shouldBe (1,MockGenerator)
  }

  it should "sss" in new RandomGenerator {
    override def nextInt: (Int, RandomGenerator) = (Int.MaxValue + Int.MinValue + 3 ,MockGenerator)
    nonNegativeInt shouldBe (2,MockGenerator)
  }*/
}
