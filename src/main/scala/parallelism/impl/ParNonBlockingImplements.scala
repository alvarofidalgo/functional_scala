package parallelism.impl

import parallelism.types.Types.{NonBlockingFuture, ParNonBlocking}


object ParNonBlockingImplements {


  implicit val integer:ParNonBlocking[Int] = (execution) => new NonBlockingFuture[Int] {

    override private[parallelism] def apply(k: (Int) => Unit): Unit = execution.submit(k)
  }

}
