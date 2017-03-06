package ramdoms


trait RandomGenerator {

  def nextInt : (Int,RandomGenerator)

  def nonNegativeInt : (Int,RandomGenerator) = {
    val nextValue = (nextInt._1+ Math.abs(Int.MinValue))%Int.MaxValue
    (nextInt._1+ Math.abs(Int.MinValue), nextInt._2)
  }
}

