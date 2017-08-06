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

  def parFilter[A](as: Seq[A])(f: A => Boolean): Par[Seq[A]] = ???

}
