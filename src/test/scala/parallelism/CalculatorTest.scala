package parallelism

import org.scalatest.FlatSpec
import org.scalatest.ShouldMatchers
import parallelism.impl.ExecutionService
import matchers.CustomMatchers._
import parallelism.api.Future

import scala.concurrent.duration.TimeUnit


class CalculatorTest extends FlatSpec with ShouldMatchers{

  case class MyFuture[A](get:A) extends Future[A] {
    override def get(timeOut: Long, unit: TimeUnit): Option[A] = Option(get)

  }


  val calulator = new Calculator()

  val executionService = new ExecutionService()


  behavior of " We want implement calculator and result "

  it should " be zero when no data " in {
    val fSum = calulator.sum(Seq.empty[Int])
    fSum(executionService)  shouldBe  futureIsEqualTo(MyFuture(get = 0))
  }


  it should " be element when have an element " in {
    val fSum = calulator.sum(Seq(1))
    fSum(executionService) shouldBe futureIsEqualTo(MyFuture(get = 1))
  }


  it should " be sum all elements when have two  elements " in {
    val fSum = calulator.sum(Seq(1,1))
    fSum(executionService) shouldBe futureIsEqualTo(MyFuture(get = 2))
  }

  it should " be sum all elements when have two or more elements " in {
    val fSum = calulator.sum(Seq(1,1,2))
    fSum(executionService)  shouldBe futureIsEqualTo(MyFuture(get =4))
  }
}
