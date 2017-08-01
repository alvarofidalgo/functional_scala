package parallelims.api

import java.util.concurrent.TimeUnit

import parallelims.impl.MyFuture
import parallelims.types.Types.Par


object ParAPI {



 /* implicit class ParConversor[A](value:A) {



  //  def unit : Par[A] = (execution) => MyFuture(get = value)

  //  def lazyUnit[A](a: =>A) : Par[A] = ???
  }*/

  implicit class ParOperations [A](par:Par[A]) {

    def map2[B, C](second: Par[B])(f: (A, B) => C): Par[C] = (execution) => {
      val firstValue = par(execution).get( 1 ,TimeUnit.NANOSECONDS)
      val secondValue = second(execution).get(1,TimeUnit.NANOSECONDS)

      MyFuture(get = f(firstValue.get, secondValue.get))
    }

    def asyncF[B](f:A=>B):A=>Par[B] = (a) => ParOperations((execution) => MyFuture(f(a))).fork




    def fork: Par[A] = (execution) =>
      execution.submit(new Callable[A] {
        override def call: A = par(execution).get
      })


  }


}
