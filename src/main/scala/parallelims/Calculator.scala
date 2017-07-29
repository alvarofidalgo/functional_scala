package parallelims


import parallelims.types.Types._

class Calculator {


  import parallelims.api.ParAPI._

  def sum(numbers:Seq[Int]):Par[Int] = numbers match{
    case Nil => 0.unit
    case head::Nil =>  head.unit
    case head::tail =>
      val (first, second) =numbers.splitAt(numbers.length / 2)
      sum(first).fork.map2(sum(second).fork) {
        (first,second)=> first + second
      }

  }
}
