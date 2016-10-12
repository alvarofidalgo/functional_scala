package simple

import org.scalatest.FlatSpec
import org.scalatest.ShouldMatchers
import simple.WorkShopTypes._
import simple.WorkShopTypes.bodyWork
import simple.WorkShopTypes.wheel

class PartialTest extends FlatSpec with ShouldMatchers {


  trait Scenerio {
       val wheel = "r"
       val bodyWork ="a"
       val car = wheel.concat(bodyWork)
  }

  " We want to implement partial function and result " should " be function that transform wheel and bodyWork in " +
    "function that build car " in new Scenerio{

    val workShop = Partial.currying[wheel, bodyWork, car]((wheel, bodyWork) => wheel.concat(bodyWork))
    workShop(wheel)(bodyWork) shouldBe car
  }


  it should " be function that unbuild workshop " in new Scenerio{
    val workShop = Partial.unCurrying[wheel, bodyWork, car]((wheel) => (bodyWork) => wheel.concat(bodyWork))
    workShop(wheel, bodyWork) shouldBe car
  }

  it should " be function that entry wheel and out car " in new Scenerio {
    val workShop = Partial.compose[wheel, bodyWork, car]((bodyWork)=> car,(wheel) => bodyWork)
    workShop(wheel) shouldBe car

  }
}
