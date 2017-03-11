package ramdoms


trait RandomGenerator {

  val limits:(Int,Int)

  def nextInt : (Int,RandomGenerator)

  def nonNegativeInt : (Int,RandomGenerator) = {
    val slowLimit = limits._1
    val upperLimit = limits._2
    val value = nextInt._1
    if (value==slowLimit)
       (0,nextInt._2)
    else if (value==slowLimit + 1)
      (1,nextInt._2)
    else
      (2,nextInt._2)
  }
}

