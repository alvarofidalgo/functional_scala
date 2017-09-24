package parallelism.blocking

import org.scalatest.FlatSpec
import org.scalatest.ShouldMatchers
import parallelism.impl.ExecutionNonBlocking
import parallelism.types.Types._


class RunnerTest extends FlatSpec with ShouldMatchers{



  behavior of " we want to implement non - blocking runner ans result  "


  it should " be result of one process when only exist one and terminate " in {
    val runner = new Runner
    val c :ParNonBlocking[Int] = (execution) => new NonBlockingFuture[Int] {

      override private[parallelism] def apply(k: (Int) => Unit): Unit = execution.submit(k)
    }
    runner.run(new ExecutionNonBlocking[Int](value = 20))(c) shouldBe 20



  }

}
