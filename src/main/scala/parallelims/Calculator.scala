package parallelims




class Calculator {


  def sum(numbers:Seq[Int]):Int = if (numbers.size<=1) {
    new Executor().unit(numbers.headOption getOrElse 0).get
  } else {
    val (first, second) =numbers.splitAt(numbers.length / 2)
    new Executor().unit(sum(first)).get + new Executor().unit(sum(second)).get
  }

}
