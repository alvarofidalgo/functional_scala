package parallelims

import parallelims.api.ParAPI


class Calculator {


  def sum(numbers:Seq[Int]):Int = if (numbers.size<=1) {
    ParAPI.unit(numbers.headOption getOrElse 0).get
  } else {
    val (first, second) =numbers.splitAt(numbers.length / 2)
     ParAPI.unit(sum(first)).get + ParAPI.unit(sum(second)).get
  }

}
