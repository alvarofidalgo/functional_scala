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
    Machine.insert(money=10,mode=INSERT)(lastStatus) shouldBe (PAYMENT,Machine(amount =10))
  }


  it should "be in PAYMENT with 20 cents when machine was in Payment with 10 cents and we inserted 10 cents " in {
    val lastStatus = Machine(amount = 10)
    Machine.insert(money=10,mode=INSERT)(lastStatus)  shouldBe (PAYMENT,Machine(amount = 20))
  }


  it should " be in SELECTION with 40 when machine was in PAYMENT with 30 cents and we inserted 10 cents " in {
    val lastStatus = Machine(amount = 30)
    Machine.insert(money=10,mode=INSERT)(lastStatus) shouldBe (SELECTED,Machine(amount=40))
  }

  it should " be in RETURN with 10 when was in PAYMENT with 50 and select product of 40 cents " in {
    val lastStatus = Machine(amount = 50)
    Machine.insert(money=40,mode=BUY)(lastStatus) shouldBe (RETURN,Machine(amount = 10))
  }

  it should " be in RETURN with 20 when was PAYMENT with 60 and select product of 40 cents " in {
    val lastStatus = Machine(amount = 60)
    Machine.insert(money=40,mode=BUY)(lastStatus) shouldBe (RETURN,Machine(amount = 20))
  }
}