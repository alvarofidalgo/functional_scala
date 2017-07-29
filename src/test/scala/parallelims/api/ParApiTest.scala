package parallelims.api

import org.scalatest.{FlatSpec, ShouldMatchers}
import parallelims.impl.{ExecutionService, MyFuture}
import parallelims.types.Types.Par

import scala.concurrent.duration.TimeUnit


import  parallelims.api.ParAPI._

class ParApiTest extends FlatSpec with ShouldMatchers{

  val executionService = new ExecutionService()


  /*case class FutureMock[A](value:A) extends Future[A]{
    override def get: A = value

    override def get(timeOut: Long, unit: TimeUnit): Option[A] = ???
  }*/

  behavior of "We want implement map2 function and result "


  it should " be new Par with data combine " in {
    val first:Par[Int] = (execution) => MyFuture(1)
    val second:Par[String] = (execution) => MyFuture("A")
    val f:(Int,String) => Double = (a,b) => a.toDouble + b.size.toDouble
    first.map2(second)(f) (executionService) shouldBe MyFuture(1.toDouble + 1.toDouble)
  }


  it should " be None when not comply time " in {
    val first:Par[Int] = (execution) => MyFuture(1)
    val second:Par[String] = (execution) => MyFuture("A")
    val f:(Int,String) => Double = (a,b) => a.toDouble + b.size.toDouble
  }

}
