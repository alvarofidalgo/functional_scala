package ramdoms

import org.junit.runner.RunWith
import org.scalatest.FlatSpec
import org.scalatest.ShouldMatchers
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
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

  it should "be max when return min sum nexValue is  max " in new MyRandomized {
    override def nextInt: (Int, RandomGenerator) = (min + 3 ,MockGenerator)
    nonNegativeInt shouldBe (3,MockGenerator)
  }

  it should "be zero when return min sum nexValue is one upper than max " in new MyRandomized {
    override def nextInt: (Int, RandomGenerator) = (min + 4 ,MockGenerator)
    nonNegativeInt shouldBe (0,MockGenerator)
  }

  it should "be one when return min sum nexValue is two upper than max " in new MyRandomized {
    override def nextInt: (Int, RandomGenerator) = (min + 5 ,MockGenerator)
    nonNegativeInt shouldBe (1,MockGenerator)
  }

  " We want to implement function that return Double between 0 and 1 and result " should " be zero when return min Value " in  new MyRandomized {
    override def nextInt: (Int, RandomGenerator) = (min,MockGenerator)
    doubleRandom shouldBe (0,MockGenerator)
  }


  it should " be 1/6 when return min + 1" in new MyRandomized {
    override def nextInt: (Int, RandomGenerator) = (min + 1 ,MockGenerator)
    doubleRandom shouldBe (1/6,MockGenerator)
  }

  it should " be 2/6 when return min +2 " in new MyRandomized {
    override def nextInt: (Int, RandomGenerator) = (min + 2 ,MockGenerator)
    doubleRandom shouldBe (2/6,MockGenerator)
  }

  it should " be zero when return min + 6 " in new MyRandomized {
    override def nextInt: (Int, RandomGenerator) = (min + 6 ,MockGenerator)
    doubleRandom shouldBe (0,MockGenerator)
  }

  it should " be 1/6 when return min + 7 " in new MyRandomized {
    override def nextInt: (Int, RandomGenerator) = (min + 7 ,MockGenerator)
    doubleRandom shouldBe (1/6,MockGenerator)
  }

  it should " be zero when return min + 12 " in new MyRandomized {
    override def nextInt: (Int, RandomGenerator) = (min + 12 ,MockGenerator)
    doubleRandom shouldBe (0,MockGenerator)
  }
}
