package parallelims.api

import org.scalatest.mock.MockitoSugar
import org.scalatest.{FlatSpec, ShouldMatchers}
import parallelims.impl.{ExecutionService, MyFuture}
import parallelims.types.Types.Par


import parallelims.api.ParAPI._



class ParApiTest extends FlatSpec with ShouldMatchers with MockitoSugar{

  val executionService = new ExecutionService()

  behavior of "We want implement map2 function and result "


  it should " be new Par with data combine " in {
    val first:Par[Int] = (execution) => MyFuture(1)
    val second:Par[String] = (execution) => MyFuture("A")
    val f:(Int,String) => Double = (a,b) => a.toDouble + b.size.toDouble
    first.map2(second)(f) (executionService) shouldBe MyFuture(1.toDouble + 1.toDouble)
  }


  behavior of " We want to implemet async behavior and result "


  it should "be computation was asynchronously " in {
    val element:Par[Int] = (execution) => MyFuture(1)
    val number = 1
    element.asyncF((a)=> a.toString)(number)(executionService).get shouldBe   s"$number"

  }


}
