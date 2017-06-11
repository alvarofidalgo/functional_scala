package parallelims




class Calculator {


  def sum(numbers:Seq[Int]):Int = if (numbers.size<=1)
    new ParImplement(numbers.headOption getOrElse  0).get
  else {
    val (first, second) =numbers.splitAt(numbers.length / 2)
    new ParImplement(sum(first)).get + new ParImplement(sum(second)).get
  }

}
