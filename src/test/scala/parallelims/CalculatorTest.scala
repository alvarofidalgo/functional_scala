package parallelims

import org.scalatest.FlatSpec
import org.scalatest.ShouldMatchers


// 0 ELEMENTOS
// 1 ELEMENTO
// 2 ELEMENTOS
// 3 ELEMETOS


class CalculatorTest extends FlatSpec with ShouldMatchers{


  val calulator = new Calculator()


  behavior of " We want implement calculator and result "

  it should " be zero when no data " in {
    calulator.sum(Seq.empty[Int]) shouldBe 0
  }


  it should " be element when have an element " in {
    calulator.sum(Seq(1)) shouldBe 1
  }


  it should " be sum all elements when have two or more elements " in {

    calulator.sum(Seq(1,1)) shouldBe 2
  }

}
