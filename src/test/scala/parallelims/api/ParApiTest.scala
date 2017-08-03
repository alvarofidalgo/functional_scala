package parallelims.api

import org.scalatest.mock.MockitoSugar
import org.scalatest.{FlatSpec, ShouldMatchers}
import parallelims.impl.ExecutionService
import parallelims.types.Types.Par
import matchers.CustomMatchers._
import parallelims.api.ParAPI._

import scala.concurrent.duration.TimeUnit



class ParApiTest extends FlatSpec with ShouldMatchers with MockitoSugar{


  case class MyFuture[A](get:A) extends Future[A] {
    override def get(timeOut: Long, unit: TimeUnit): Option[A] = Option(get)

  }

  behavior of "We want implement map2 function and result "


  it should " be new Par with data combine " in {
    val first:Par[Int] = (execution) => execution.submit(MyCallable(callReturn = 1) )
    val second:Par[String] = (execution) => execution.submit(MyCallable(callReturn = "A") )
    val f:(Int,String) => Double = (a,b) => a.toDouble + b.size.toDouble
    val expected = MyFuture( get = 1.toDouble + 1.toDouble)
    execute(first.map2(second)(f))  shouldBe futureIsEqualTo(expected)
  }


  behavior of " We want to implemet async behavior and result "


  it should "be computation was asynchronously " in {
    val element:Par[Int] = (execution) => execution.submit(MyCallable(callReturn = 1) )
    val number = 1
    val expected = MyFuture(get = s"$number")
    execute(element.asyncF((a)=> a.toString)(number)) shouldBe   futureIsEqualTo(expected)
  }



  behavior of " We want to implement map function in parAPI and result "

  it should " be new Par[Boolean] when apply f(Int)=>Boolean " in {
    val element:Par[Int] = (execution) => execution.submit(MyCallable(callReturn = 1) )
    val f:(Int)=>(Boolean) = (a) => a == 1
    val expected = MyFuture(true)
    execute(element map f) shouldBe futureIsEqualTo(expected)
  }


  behavior of " We want to implement parMap function and result "
//def parMap[A,B](ps: List[A])(f: A => B): Par[List[B]]
  it should " be empty List with Paralelism computation when entry was empty " in {

  }

  def execute[A](f:(ExecutionService)=>A):A = f(new ExecutionService())
}
