package parallelims.api

import org.scalatest.mock.MockitoSugar
import org.scalatest.{FlatSpec, ShouldMatchers}
import parallelims.impl.{ExecutionService, MyFuture}
import parallelims.types.Types.Par


import parallelims.api.ParAPI._



class ParApiTest extends FlatSpec with ShouldMatchers with MockitoSugar{


  case class MyCallable[A] ( callReturn:A) extends Callable[A] {

    override def call: A = callReturn
  }

  behavior of "We want implement map2 function and result "


  it should " be new Par with data combine " in {
    val first:Par[Int] = (execution) => execution.submit(MyCallable(callReturn = 1) )
    val second:Par[String] = (execution) => execution.submit(MyCallable(callReturn = "A") )
    val f:(Int,String) => Double = (a,b) => a.toDouble + b.size.toDouble
    execute(first.map2(second)(f))  shouldBe MyFuture(1.toDouble + 1.toDouble)
  }


  behavior of " We want to implemet async behavior and result "


  it should "be computation was asynchronously " in {
    val element:Par[Int] = (execution) => execution.submit(MyCallable(callReturn = 1) )
    val number = 1
    execute(element.asyncF((a)=> a.toString)(number)).get shouldBe   s"$number"
  }



  behavior of " We want to implement map function in parAPI and result "

  it should " be new Par[Boolean] when apply f(Int)=>Boolean " in {
    val element:Par[Int] = (execution) => execution.submit(MyCallable(callReturn = 1) )
    val expected:Par[Boolean] = (execution) => MyFuture(true)
    val f:(Int)=>(Boolean) = (a) => a == 1
    execute(element.map(f)) shouldBe execute(expected)
  }


  behavior of " We want to implement parMap function and result "
//def parMap[A,B](ps: List[A])(f: A => B): Par[List[B]]
  it should " be empty List with Paralelism computation when entry was empty " in {

  }

  def execute[A](f:(ExecutionService)=>A) = f(new ExecutionService())
}
