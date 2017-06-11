package parallelims

import parallelims.api.ParAPI


class Calculator {


  def sum(numbers:Seq[Int]):Int = numbers match{
    case Nil => ParAPI.get(ParAPI.unit(0))
    case head::Nil =>  ParAPI.get(ParAPI.unit(head))
    case head::tail =>
      val (first, second) =numbers.splitAt(numbers.length / 2)
      ParAPI.map2(ParAPI.unit(sum(first)),ParAPI.unit(sum(second))){
        (first,second)=> {
          first + second
        }
      }.get
  }


}
