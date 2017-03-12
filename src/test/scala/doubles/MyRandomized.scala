package doubles

import ramdoms.RandomGenerator


trait MyRandomized extends RandomGenerator {
  val min: Int = -3
  val max: Int = 3
  override val limits: (Int, Int) = (min,max)
}

object MockGenerator extends MyRandomized {


  override def nextInt: (Int, RandomGenerator) = ???

}

case class DoubleRandomized(min:Int,max:Int,next:Int) extends RandomGenerator{
  override val limits: (Int, Int) = (min,max)

  override def nextInt: (Int, RandomGenerator) = (next,MockGenerator)
}
