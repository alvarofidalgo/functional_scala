package dispenser

import org.junit.runner.RunWith
import org.scalatest.FlatSpec
import org.scalatest.ShouldMatchers
import org.scalatest.junit.JUnitRunner


@RunWith(classOf[JUnitRunner])
class MachineTest extends FlatSpec with ShouldMatchers {

  behavior of " Coffee Machine"


  it should " be in  PAYMENT with 10 cents when machine was in PAYMENT with 0 cents and we inserted 10 cents " in {
    Machine(amount = 0).insert(money=10)(Machine(amount = 10)) shouldBe ((PAYMENT,10),Machine(10))
  }


  it should "be in PAYMENT with 20 cents when machine was in Payment with 10 cents and we inserted 10 cents " in {
    Machine(amount = 10).insert(money=10)(Machine(amount = 20))  shouldBe ((PAYMENT,20),Machine(20))
  }


  it should " be in SELECTION with 40 when machine was in PAYMENT with 30 cents and we inserted 10 cents " in {
    Machine(amount = 30).insert(money=10)(Machine(amount = 10)) shouldBe ((SELECTED,40),Machine(40))
  }
}
