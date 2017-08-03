package parallelims.impl

import parallelims.api.Future

import scala.concurrent.duration._


case class MyFuture[A](get:A,a:Int) extends Future[A] {
  override def get(timeOut: Long, unit: TimeUnit): Option[A] = Option(get)

}

