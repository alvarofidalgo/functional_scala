package parallelism.types

import parallelism.api.Future
import parallelism.impl.{ExecutionNonBlocking, ExecutionService}


object Types {




  type Par[A] = (ExecutionService) => Future[A]


  trait NonBlockingFuture[A] {

    private [parallelism] def apply(k:A => Unit ) : Unit

  }

  type ParNonBlocking[B] = (ExecutionNonBlocking[B]) => NonBlockingFuture[B]




}
