package parallelims.api

import java.util.concurrent.TimeUnit

import parallelims.impl.MyFuture
import parallelims.types.Types.Par


object ParAPI {



  implicit class ParOperations [A](par:Par[A]) {

    

    def map2[B, C](second: Par[B])(f: (A, B) => C): Par[C] = (execution) => {
      val firstValue = par(execution).get( 1 ,TimeUnit.NANOSECONDS)
      val secondValue = second(execution).get(1,TimeUnit.NANOSECONDS)

      MyFuture(get = f(firstValue.get, secondValue.get))
    }

    def asyncF[B](f:A=>B):A=>Par[B] = (a) => {
     val res:Par[B] = (execution) => MyFuture(f(a))
      res.fork
    }

    def fork: Par[A] = (execution) =>
          execution.submit(new Callable[A] {
            override def call: A = par(execution).get
          })



  }


}
