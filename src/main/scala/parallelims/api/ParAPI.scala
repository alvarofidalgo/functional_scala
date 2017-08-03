package parallelims.api

import java.util.concurrent.TimeUnit
import parallelims.types.Types.Par


object ParAPI {

  implicit class ParOperations [A](par:Par[A]) {

    def asyncF[B](f:A=>B):A=>Par[B] = (a) => {
      val res:Par[B] = (execution) => execution.submit(MyCallable(callReturn = f(a)))
      res.fork
    }

    def fork: Par[A] = (execution) =>
      execution.submit(new Callable[A] {
        override def call: A = par(execution).get
      })


    def map[B](f:(A)=>B): Par[B] = {
      val parUnit:Par[Unit]  = (execution)=> execution.submit(MyCallable(callReturn = ()))
      val transform:(A,Any) => B = (a,_)=>f(a)
      par.map2(parUnit)(transform)
    }



    def map2[B, C](second: Par[B])(f: (A, B) => C): Par[C] = (execution) => {
      val firstValue = par(execution).get( 1 ,TimeUnit.NANOSECONDS)
      val secondValue = second(execution).get(1,TimeUnit.NANOSECONDS)
      execution.submit(MyCallable(callReturn = f(firstValue.get, secondValue.get)))
    }







  }


}
