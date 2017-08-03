package parallelims.api

import matchers.CustomMatchers._
import org.scalatest.{FlatSpec, ShouldMatchers}
import parallelims.api.Sequence._

class SequenceTest extends FlatSpec with ShouldMatchers with ParallelimsOpTest{


  behavior of " We want to implement parMap function and result "

  it should " be empty List with Paralelism computation when entry was empty " in {

      val sequence:Seq[Int] = Seq.empty[Int]
      val f:(Int) => Boolean = (num) => num<2
      val expected = MyFuture(get = Seq.empty[Boolean])
      execute(sequence.parMap(f)) shouldBe futureIsEqualTo(expected)

  }


}
