package parallelims




class Calculator {
  

  def sum(numbers:Seq[Int]):Int = if (numbers.size<=1)
    numbers.headOption getOrElse  0
  else {
    val (first, second) = numbers.splitAt(numbers.length / 2)
    sum(first) + sum(second)
  }

}
