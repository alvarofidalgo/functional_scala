package parallelism


import parallelism.api.MyCallable
import parallelism.types.Types._

class Calculator {

  import parallelism.api.ParAPI._

  //FIXME : Make tailrec
  def sum(numbers:Seq[Int]):Par[Int] = numbers match{
    case Nil => (execution) => execution.submit(MyCallable(callReturn = 0))
    case head::Nil =>  (execution) => execution.submit(MyCallable(callReturn = head))
    case _ =>
      val (first, second) =numbers.splitAt(numbers.length / 2)
      sum(first).fork.map2(sum(second).fork) {
        (first,second)=> first + second
      }

  }
}
