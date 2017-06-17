package parallelims.api

import parallelims.types.Types.Par

import scala.concurrent.duration.TimeUnit

object ParAPI {


  private case class MyFuture[A](get:A) extends Future[A] {
    override def get(timeOut: Long, unit: TimeUnit): A = ???
  }


  def unit[A](unit: => A):Par[A] = (execution) => MyFuture(get=unit)


  def map2[A,B,C](first:Par[A],second:Par[B])(f:(A,B)=>C):Par[C] = (execution) => {
    val firstValue = first(execution).get
    val secondValue = second(execution).get
    MyFuture(get=f(firstValue,secondValue))
  }


  def fork[A](a: => Par[A]):Par[A] = (execution) => {
    execution.submit(new Callable[A] {
      override def call: A = a(execution).get
    })

  }


}
