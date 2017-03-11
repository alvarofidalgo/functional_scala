package ramdoms


trait RandomGenerator {

  val limits:(Int,Int)

  def nextInt : (Int,RandomGenerator)

  def nonNegativeInt : (Int,RandomGenerator) = {
    val min = limits._1
    val max = limits._2
    val realValue = (nextInt._1 - min)%(max+1)
    (realValue,nextInt._2)
  }


  def doubleRandom : (Double,RandomGenerator) = {
    val road = limits._2 - limits._1
    if (nextInt._1 >= (limits._1 + 6))
      (nextInt._1 - (road + limits._1),nextInt._2)
    else
       ((nextInt._1 - limits._1)/road,nextInt._2)
  }
}

