package parallelism.api

import org.scalatest.{FlatSpec, ShouldMatchers}
import parallelism.types.Types.Par



class ParApiTest extends FlatSpec with ShouldMatchers {


  import matchers.CustomMatchers._
  behavior of "We want implement map2 function and result "


  it should " be new Par with data combine " in {
    val first:Par[Int] = (execution) => execution.submit(MyCallable(callReturn = 1) )
    val second:Par[String] = (execution) => execution.submit(MyCallable(callReturn = "A") )
    val expected:Par[Double] =(execution) => execution.submit(MyCallable( callReturn = 1.toDouble + 1.toDouble))
    val f:(Int,String) => Double = (a,b) => a.toDouble + b.size.toDouble
    ParAPI(par =first).map2(second)(f) shouldBe parallelismIsEqualTo(expected)
  }




  behavior of " We want to implement map function in parAPI and result "

  it should " be new Par[Boolean] when apply f(Int)=>Boolean " in {
    val element:Par[Int] = (execution) => execution.submit(MyCallable(callReturn = 1) )
    val expected:Par[Boolean] = (execution) => execution.submit( MyCallable(callReturn = true))
    val f:(Int)=>(Boolean) = (a) => a == 1
    ParAPI(par =element).map(f) shouldBe parallelismIsEqualTo(expected)
  }
}
