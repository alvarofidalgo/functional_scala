package parallelism


import parallelism.api.MyCallable
import parallelism.types.Types._

class Calculator {

  import parallelism.api.ParAPI

  //FIXME : Make tailrec
  def sum(numbers:Seq[Int]):Par[Int] = numbers match{
    case Nil => (execution) => execution.submit(MyCallable(callReturn = 0))
    case head::Nil =>  (execution) => execution.submit(MyCallable(callReturn = head))
    case _ =>
      val (first, second) =numbers.splitAt(numbers.length / 2)
      val firstSum = ParAPI(sum(first)).fork
      val secondSum = ParAPI(sum(second)).fork
      ParAPI(firstSum).map2(secondSum) {
        (first,second)=> first + second
      }

  }
}
