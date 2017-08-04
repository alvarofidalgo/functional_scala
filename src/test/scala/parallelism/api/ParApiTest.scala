package parallelism.api

import org.scalatest.{FlatSpec, ShouldMatchers}
import parallelism.impl.ExecutionService
import parallelism.types.Types.Par
import matchers.CustomMatchers._
import parallelism.api.ParAPI._

import scala.concurrent.duration.TimeUnit



class ParApiTest extends FlatSpec with ShouldMatchers with ParallelismOpTest{



  behavior of "We want implement map2 function and result "


  it should " be new Par with data combine " in {
    val first:Par[Int] = (execution) => execution.submit(MyCallable(callReturn = 1) )
    val second:Par[String] = (execution) => execution.submit(MyCallable(callReturn = "A") )
    val f:(Int,String) => Double = (a,b) => a.toDouble + b.size.toDouble
    val expected = MyFuture( get = 1.toDouble + 1.toDouble)
    execute(first.map2(second)(f))  shouldBe futureIsEqualTo(expected)
  }




  behavior of " We want to implement map function in parAPI and result "

  it should " be new Par[Boolean] when apply f(Int)=>Boolean " in {
    val element:Par[Int] = (execution) => execution.submit(MyCallable(callReturn = 1) )
    val f:(Int)=>(Boolean) = (a) => a == 1
    val expected = MyFuture(true)
    execute(element map f) shouldBe futureIsEqualTo(expected)
  }

}
