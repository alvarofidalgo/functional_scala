package parallelism.api

import parallelism.types.Types._


object Asynchronous {

 implicit  class Async[A](value: A) {

    def asyncF[B](f: A => B): Par[B] = {
      val res: Par[B] = (execution) => execution.submit(MyCallable(callReturn = f(value)))
      ParAPI(res).fork
    }

  }

}
