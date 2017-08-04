package parallelism.api

import matchers.CustomMatchers._
import org.scalatest.{FlatSpec, ShouldMatchers}
import parallelism.types.Types._


class AsyncTest extends FlatSpec with ShouldMatchers with ParallelismOpTest{


  behavior of " We want to implemet async behavior and result "


  it should "be computation was asynchronously " in {
    val element:Par[Int] = (execution) => execution.submit(MyCallable(callReturn = 1) )
    val number = 1
    val expected = MyFuture(get = s"$number")
    val async = Async(number)
    execute(async.asyncF((a)=> a.toString)) shouldBe   futureIsEqualTo(expected)
  }


}
