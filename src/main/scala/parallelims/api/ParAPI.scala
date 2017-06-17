package parallelims.api

import parallelims.impl.MyFuture
import parallelims.types.Types.Par


object ParAPI {



  implicit class ParConversor[A](value:A) {


    def unit : Par[A] = (execution) => MyFuture(get = value)
  }

  implicit class ParOperations [A](par:Par[A]) {

    def map2[B, C](second: Par[B])(f: (A, B) => C): Par[C] = (execution) => {
      val firstValue = par(execution).get
      val secondValue = second(execution).get
      MyFuture(get = f(firstValue, secondValue))
    }


    def fork: Par[A] = (execution) => {
      execution.submit(new Callable[A] {
        override def call: A = par(execution).get
      })

    }
  }


}
