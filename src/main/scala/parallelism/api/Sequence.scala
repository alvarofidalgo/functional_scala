package parallelism.api

import parallelism.types.Types.Par



object Sequence {


  def parMap[A,B](entrySequence: Seq[A])(f: A => B): Par[Seq[B]] = (execution) =>
    execution.submit(new Callable[Seq[B]] {
      override def call: Seq[B] = entrySequence.map(f)
    })


  def sequence[A](entrySequence: Seq[Par[A]]): Par[Seq[A]] = (execution) =>{
    val seq = entrySequence.map {
      par => par(execution).get
    }
    execution.submit(MyCallable(callReturn = seq))
  }

}
