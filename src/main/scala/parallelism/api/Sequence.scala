package parallelism.api

import parallelism.types.Types.Par


object Sequence {


  implicit class SequenceOperations[A](entrySequence: Seq[A]){

    def parMap[B](f: A => B): Par[Seq[B]] = (execution) =>
      execution.submit(new Callable[Seq[B]] {
          override def call: Seq[B] = entrySequence.map(f)
    })


  }


  implicit class SequenceOperations1[A](entrySequence: Seq[Par[A]]){



    def sequence: Par[Seq[A]] = (execution) =>{
      val seq = entrySequence.map {
        par => par(execution).get
      }
      execution.submit(MyCallable(callReturn = seq))
    }
  }

}
