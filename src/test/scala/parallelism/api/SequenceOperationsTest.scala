package parallelism.api





import matchers.CustomMatchers._
import org.scalatest.{FlatSpec, ShouldMatchers}
import parallelism.api.SequenceOp._
import parallelism.types.Types.Par


class SequenceOperationsTest extends FlatSpec with ShouldMatchers {



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

  behavior of " We want implement parFilter function and result "

  it should " be empty PAr when Par is empty " in {

    val expected:Par[Seq[Int]]  = (execution) => execution.submit( MyCallable( callReturn = Nil))

    Seq.empty[Int].parFilter({(a)=> true }) shouldBe parallelismIsEqualTo(expected)
  }


  it should " be Par with initial secuence when cobndition return true " in {
    val sequence = Seq(1,2,3)
    val expected:Par[Seq[Int]]  = (execution) => execution.submit( MyCallable( callReturn = sequence))

    sequence.parFilter({(a)=> true }) shouldBe parallelismIsEqualTo(expected)
  }


  it should " be PAr with elements that complain condition " in {
    val sequence = Seq(1,2,3,4)
    val expected:Par[Seq[Int]]  = (execution) => execution.submit( MyCallable( callReturn = Seq(2,4)))

    sequence.parFilter({(a)=> a % 2 ==0 }) shouldBe parallelismIsEqualTo(expected)
  }
}
