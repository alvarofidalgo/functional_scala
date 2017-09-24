package parallelism.blocking

import java.util.concurrent.CountDownLatch

import parallelism.impl.ExecutionNonBlocking
import parallelism.types.Types.ParNonBlocking


class Runner {


  def run[A](executionService: ExecutionNonBlocking[A])(p:ParNonBlocking[A]):A = {
    var res = Seq.empty[A]
    val latch = new CountDownLatch(1)
    p(executionService) { (a:A) =>

      res = res ++ Seq(a)
      latch.countDown()

    }
    latch.await()
    res.head
  }

}
