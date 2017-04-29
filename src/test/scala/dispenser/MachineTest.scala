package dispenser

import org.junit.runner.RunWith
import org.scalatest.FlatSpec
import org.scalatest.ShouldMatchers
import org.scalatest.junit.JUnitRunner


@RunWith(classOf[JUnitRunner])
class MachineTest extends FlatSpec with ShouldMatchers {

  behavior of " Coffee Machine"


  it should " be in  PAYMENT with 10 cents when machine was in PAYMENT with 0 cents and we inserted 10 cents " in {
    val lastStatus = Machine(amount =0)
    Machine.insert(money=10)(lastStatus) shouldBe (PAYMENT,Machine(10))
  }


  it should "be in PAYMENT with 20 cents when machine was in Payment with 10 cents and we inserted 10 cents " in {
    val lastStatus = Machine(amount = 10)
    Machine.insert(money=10)(lastStatus)  shouldBe (PAYMENT,Machine(20))
  }


  it should " be in SELECTION with 40 when machine was in PAYMENT with 30 cents and we inserted 10 cents " in {
    val lastStatus = Machine(amount = 30)
    Machine.insert(money=10)(lastStatus) shouldBe (SELECTED,Machine(40))
  }
}