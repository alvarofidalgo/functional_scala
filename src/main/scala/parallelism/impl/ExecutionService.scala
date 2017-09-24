package parallelism.impl

import parallelism.api.{Callable, Future}
import parallelism.types.Types.NonBlockingFuture

import scala.concurrent.duration.TimeUnit



class ExecutionService {


    def submit[A](callable: Callable[A]):Future[A] = new Future[A] {
      override def get: A = callable.call

      override def get(timeOut: Long, unit: TimeUnit): Option[A] = Option(callable.call)
    }

}


class ExecutionNonBlocking[A](value:A) {



  def submit(f:A=>Unit):Unit = f(value)

}
