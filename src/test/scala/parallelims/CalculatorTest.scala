package parallelims

import org.scalatest.FlatSpec
import org.scalatest.ShouldMatchers


class CalculatorTest extends FlatSpec with ShouldMatchers{


  val calulator = new Calculator()


  behavior of " We want implement calculator and result "

  it should " be zero when no data " in {
    calulator.sum(Seq.empty[Int]).get  shouldBe 0
  }


  it should " be element when have an element " in {
    calulator.sum(Seq(1)).get  shouldBe 1
  }


  it should " be sum all elements when have two  elements " in {

    calulator.sum(Seq(1,1)).get shouldBe 2
  }

  it should " be sum all elements when have two or more elements " in {

    calulator.sum(Seq(1,1,2)).get  shouldBe 4
  }

}
