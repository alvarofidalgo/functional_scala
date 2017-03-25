package ramdoms


// TODO : EXTRAER LOS MÉTODOS
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
    val min = limits._1
    val max = limits._2
    val road = max - min
    val realValue = ((nextInt._1 - min)%max).toDouble/road.toDouble
    (realValue.toDouble,nextInt._2)
  }


  def intDoubleRandom:((Int,Double),RandomGenerator) = ((nonNegativeInt._1,doubleRandom._1),nextInt._2)
}

