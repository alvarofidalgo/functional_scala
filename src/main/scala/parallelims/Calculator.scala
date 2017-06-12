package parallelims

import parallelims.api.{Future, ParAPI}


class Calculator {


  def sum(numbers:Seq[Int]):Future[Int] = numbers match{
    case Nil => ParAPI.unit(0)
    case head::Nil =>  ParAPI.unit(head)
    case head::tail =>
      val (first, second) =numbers.splitAt(numbers.length / 2)
      ParAPI.map2(ParAPI.fork(sum(first)),ParAPI.fork(sum(second))){
         (first,second)=> first + second
       }
  }
}
