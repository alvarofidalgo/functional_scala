package parallelism.api

import matchers.CustomMatchers._
import org.scalatest.{FlatSpec, ShouldMatchers}
import parallelism.api.Sequence._
import parallelism.types.Types._

class SequenceTest extends FlatSpec with ShouldMatchers {



  behavior of " We want to implement secuence function and result "

  it should " be Empty Par of List when List of Par is empty " in {
    val element:Seq[Par[Int]] = Seq.empty[Par[Int]]
    val expected:Par[Seq[Int]]  = (execution) => execution.submit( MyCallable( callReturn = Seq.empty[Int]))
    sequence(element)  shouldBe   parallelismIsEqualTo(expected)
  }


  it should " be non Empty par of List when List of Par is not empty " in {
    val element:Seq[Par[Int]] = Seq((execution) => execution.submit( MyCallable( callReturn = 1)))
    val expected:Par[Seq[Int]]  = (execution) => execution.submit( MyCallable( callReturn = Seq(1)))
    sequence(element)  shouldBe parallelismIsEqualTo(expected)
  }





}
