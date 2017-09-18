package parallelism.api

import matchers.CustomMatchers._
import org.scalatest.{FlatSpec, ShouldMatchers}
import parallelism.api.Asynchronous._
import parallelism.types.Types._



class AsyncTest extends FlatSpec with ShouldMatchers{


  behavior of " We want to implemet async behavior and result "


  it should "be computation was asynchronously " in {
    val number = 1
    val expected:Par[String] =(execution) => execution.submit(MyCallable(callReturn = s"$number"))
    number.asyncF((a)=> a.toString) shouldBe   parallelismIsEqualTo(expected)
  }


}
