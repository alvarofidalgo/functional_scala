package ramdoms


trait RandomGenerator {

  def nextInt : (Int,RandomGenerator)

  def nonNegativeInt : (Int,RandomGenerator) = {

    val cValue:Int = ~Int.MinValue + nextInt._1
    val k = Int.MaxValue
    val realValue = if (cValue<k) cValue else 0
    (nextInt._1 + ~Int.MinValue +1 , nextInt._2)
  }
}

