package parallelims

import org.scalatest.FlatSpec
import org.scalatest.ShouldMatchers
import parallelims.api.ExecutionService


class CalculatorTest extends FlatSpec with ShouldMatchers{


  val calulator = new Calculator()

  val executionService = new ExecutionService()


  behavior of " We want implement calculator and result "

  it should " be zero when no data " in {
    val fSum = calulator.sum(Seq.empty[Int])
    val futureSum = fSum(executionService)
    futureSum.get  shouldBe 0
  }


  it should " be element when have an element " in {
    val fSum = calulator.sum(Seq(1))
    val futureSum = fSum(executionService)
    futureSum.get shouldBe 1
  }


  it should " be sum all elements when have two  elements " in {
    val fSum = calulator.sum(Seq(1,1))
    val futureSum = fSum(executionService)
    futureSum.get shouldBe 2
  }

  it should " be sum all elements when have two or more elements " in {
    val fSum = calulator.sum(Seq(1,1,2))
    val futureSum = fSum(executionService)
    futureSum.get  shouldBe 4
  }



}
