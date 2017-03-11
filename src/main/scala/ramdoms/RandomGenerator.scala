package ramdoms


trait RandomGenerator {

  val limits:(Int,Int)

  def nextInt : (Int,RandomGenerator)

  def nonNegativeInt : (Int,RandomGenerator) = {
    val min = limits._1
    val max = limits._2
    val value = nextInt._1
    (value - min,nextInt._2)
  }
}

