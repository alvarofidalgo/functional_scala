package parallelism.api

import parallelism.types.Types.Par


object Sequence {



  def sequence[A](entrySequence: Seq[Par[A]]): Par[Seq[A]] = (execution) =>{
    val seq = entrySequence.map {
      par => par(execution).get
    }
    execution.submit(MyCallable(callReturn = seq))
  }

}
