package parallelism.api

import matchers.CustomMatchers._
import org.scalatest.{FlatSpec, ShouldMatchers}
import parallelism.api.Sequence._
import parallelism.types.Types._

class SequenceTest extends FlatSpec with ShouldMatchers {


  behavior of " We want to implement parMap function and result "

  it should " be empty List with Paralelism computation when entry was empty " in {

      val sequence:Seq[Int] = Seq.empty[Int]
      val f:(Int) => Boolean = (num) => num<2
      val expected:Par[Seq[Boolean]] = (execution) => execution.submit(MyCallable(callReturn = Seq.empty[Boolean]))
      sequence.parMap(f) shouldBe parallelismIsEqualTo(expected)

  }

  it should " be non empty List with Paralelism computation when entry was empty  " in  {
    val sequence:Seq[Int] = Seq(3)
    val f:(Int) => Boolean = (num) => num<2
    val expected:Par[Seq[Boolean]] = (execution) => execution.submit(MyCallable(callReturn = Seq(false)))
    sequence.parMap(f) shouldBe parallelismIsEqualTo(expected)
  }


}
