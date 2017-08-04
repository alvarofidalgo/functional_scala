package parallelism.api

import parallelism.types.Types.Par


object Sequence {


  implicit class SequenceOperations[A](sequence: Seq[A]){

    def parMap[B](f: A => B): Par[Seq[B]] = (execution) =>
      execution.submit(new Callable[Seq[B]] {
          override def call: Seq[B] = sequence.map(f)
    })
  }

}
