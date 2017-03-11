package ramdoms


trait RandomGenerator {

  val limits:(Int,Int)

  def nextInt : (Int,RandomGenerator)

  def nonNegativeInt : (Int,RandomGenerator) = {
    val slowLimit = limits._1
    val upperLimit = limits._2
    val value = nextInt._1
    (value - slowLimit,nextInt._2)
  }
}

