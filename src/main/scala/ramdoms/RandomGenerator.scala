package ramdoms


trait RandomGenerator {

  val limits:(Int,Int)

  def nextInt : (Int,RandomGenerator)

  def nonNegativeInt : (Int,RandomGenerator) = {
    val min = limits._1
    val max = limits._2
    val value = nextInt._1
    if (value - min == max + 1)
      (0,nextInt._2)
    else if (value - min == max +2)
      (1,nextInt._2)
    else
    (value - min,nextInt._2)
  }
}

