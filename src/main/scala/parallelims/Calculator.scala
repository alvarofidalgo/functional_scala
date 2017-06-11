package parallelims

import parallelims.api.ParAPI


class Calculator {


  def sum(numbers:Seq[Int]):Int = numbers match{
    case Nil => ParAPI.unit(0).get
    case head::Nil =>  ParAPI.unit(head).get
    case head::tail =>
      val (first, second) =numbers.splitAt(numbers.length / 2)
      ParAPI.get(ParAPI.unit(sum(first))) + ParAPI.get(ParAPI.unit(sum(second)))
  }


}
