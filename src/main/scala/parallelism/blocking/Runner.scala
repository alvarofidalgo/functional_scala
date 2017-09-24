package parallelism.blocking

import java.util.concurrent.CountDownLatch

import parallelism.impl.{ExecutionNonBlocking, ExecutionService}
import parallelism.types.Types.{NonBlockingFuture, ParNonBlocking}


class Runner {


  def run[A](executionService: ExecutionNonBlocking[A])(p:ParNonBlocking[A]):A = {
    var res = Seq.empty[A]
   // val d:NonBlockingFuture[A] =  p(executionService)
   val latch = new CountDownLatch(1)
    val k = p(executionService)
    k({ (a:A) =>

      res = res ++ Seq(a)
       latch.countDown()

    })
    latch.await()
    res.head
  }

}
