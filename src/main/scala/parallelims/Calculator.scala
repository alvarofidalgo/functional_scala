package parallelims


import parallelims.impl.MyFuture
import parallelims.types.Types._

class Calculator {


  import parallelims.api.ParAPI._

  def sum(numbers:Seq[Int]):Par[Int] = numbers match{
    case Nil => (execution) => MyFuture(get = 0)
    case head::Nil =>  (execution) => MyFuture(get = head)
    case head::tail =>
      val (first, second) =numbers.splitAt(numbers.length / 2)
      sum(first).fork.map2(sum(second).fork) {
        (first,second)=> first + second
      }

  }
}
