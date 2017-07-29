package parallelims.api

import scala.concurrent.duration.TimeUnit

trait Future [A] {


  def get:A

  def get(timeOut:Long,unit:TimeUnit):Option[A]

}
