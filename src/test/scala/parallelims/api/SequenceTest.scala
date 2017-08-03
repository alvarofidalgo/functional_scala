package parallelims.api

import matchers.CustomMatchers._
import org.scalatest.{FlatSpec, ShouldMatchers}
import parallelims.impl.ExecutionService

import scala.concurrent.duration.TimeUnit

import parallelims.api.Sequence._

class SequenceTest extends FlatSpec with ShouldMatchers{

  case class MyFuture[A](get:A) extends Future[A] {
    override def get(timeOut: Long, unit: TimeUnit): Option[A] = Option(get)

  }
  behavior of " We want to implement parMap function and result "
  //def parMap[A,B](ps: Seq[A])(f: A => B): Par[Seq[B]]
  it should " be empty List with Paralelism computation when entry was empty " in {

      val sequence:Seq[Int] = Seq.empty[Int]
      val f:(Int) => Boolean = (num) => num<2
      val expected = MyFuture(get = Seq.empty[Boolean])
      execute(sequence.parMap(f)) shouldBe futureIsEqualTo(expected)

  }


  def execute[A](f:(ExecutionService)=>A):A = f(new ExecutionService())

}
