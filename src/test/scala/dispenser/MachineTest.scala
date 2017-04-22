package dispenser

import org.junit.runner.RunWith
import org.scalatest.FlatSpec
import org.scalatest.ShouldMatchers
import org.scalatest.junit.JUnitRunner


@RunWith(classOf[JUnitRunner])
class MachineTest extends FlatSpec with ShouldMatchers {

  behavior of " Coffee Machine"

  val machine = new Machine()

  it should " be in  PAYMENT with 10 cents when machine was in PAYMENT with 0 cents and we inserted 10 cents " in {
    machine.insert(total=0,money=10) shouldBe ("PAYMENT",10)
  }


  it should "be in PAYMENT wwith 20 cents when machine was in Payment with 10 cents and we inserted 10 cents " in {
    machine.insert(total=10,money=10) shouldBe ("PAYMENT",20)
  }



}
