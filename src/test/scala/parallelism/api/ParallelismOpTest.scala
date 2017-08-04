package parallelism.api

import parallelism.impl.ExecutionService

import scala.concurrent.duration.TimeUnit


trait ParallelismOpTest {
  case class MyFuture[A](get:A) extends Future[A] {
    override def get(timeOut: Long, unit: TimeUnit): Option[A] = Option(get)

  }

  def execute[A](f:(ExecutionService)=>A):A = f(new ExecutionService())
}
