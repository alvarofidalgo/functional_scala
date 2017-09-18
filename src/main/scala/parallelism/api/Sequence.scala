package parallelism.api

import parallelism.types.Types.Par


object Sequence {


  def parMap[A,B](entrySequence: Seq[A])(f: A => B): Par[Seq[B]] = {
    import Asynchronous._
    val list = entrySequence.map((value) => value.asyncF(f))
    sequence(list)
  }


  def sequence[A](entrySequence: Seq[Par[A]]): Par[Seq[A]] = (execution) =>{
    val seq = entrySequence.map {
      par => par(execution).get
    }
    execution.submit(MyCallable(callReturn = seq))
  }

  def parFilter[A](as: Seq[A])(f: A => Boolean): Par[Seq[A]] =  {
    import Asynchronous._
    val list1 = as.foldLeft(Seq.empty[Par[A]]) {
      (res,head) => f(head) match {
        case true => res ++ Seq(head.asyncF((a)=>a))
        case _ => res
      }

    }
    sequence(list1)
  }

}
