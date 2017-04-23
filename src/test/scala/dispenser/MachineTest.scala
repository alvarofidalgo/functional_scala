package dispenser

import org.junit.runner.RunWith
import org.scalatest.FlatSpec
import org.scalatest.ShouldMatchers
import org.scalatest.junit.JUnitRunner


@RunWith(classOf[JUnitRunner])
class MachineTest extends FlatSpec with ShouldMatchers {

  behavior of " Coffee Machine"

  val machine = new Machine(state = "PAYMENT",amount = 0)



  it should " be in  PAYMENT with 10 cents when machine was in PAYMENT with 0 cents and we inserted 10 cents " in {
    Machine(state = "PAYMENT",amount = 0).insert(money=10) shouldBe Machine("PAYMENT",10)
  }


  it should "be in PAYMENT with 20 cents when machine was in Payment with 10 cents and we inserted 10 cents " in {
    Machine(state = "PAYMENT",amount = 10).insert(money=10) shouldBe Machine("PAYMENT",20)
  }


  it should " be in SELECTION with 40 when machine was in PAYMENT with 30 cents and we inserted 10 cents " in {
    Machine(state = "PAYMENT",amount = 30).insert(money=10) shouldBe Machine("SELECTION",40)
  }



}
