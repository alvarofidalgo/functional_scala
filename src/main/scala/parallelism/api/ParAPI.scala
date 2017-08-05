package parallelism.api

import java.util.concurrent.TimeUnit
import parallelism.types.Types.Par


case class ParAPI[A](par:Par[A]) {



    def fork: Par[A] = (execution) =>
      execution.submit(new Callable[A] {
        override def call: A = par(execution).get
    })

    def map[B](f:(A)=>B): Par[B] = {
      val parUnit:Par[Unit]  = (execution)=> execution.submit(MyCallable(callReturn = ()))
      val transform:(A,Any) => B = (a,_)=>f(a)
      map2(parUnit)(transform)
    }

    def map2[B, C](second: Par[B])(f: (A, B) => C): Par[C] = (execution) => {
      val firstValue = par(execution).get(1, TimeUnit.NANOSECONDS)
      val secondValue = second(execution).get(1, TimeUnit.NANOSECONDS)
      execution.submit(MyCallable(callReturn = f(firstValue.get, secondValue.get)))
    }

    def sequence[A](ps: Seq[Par[A]]): Par[Seq[A]] = (execution) => execution.submit(MyCallable(callReturn = Seq.empty[A]))

}
