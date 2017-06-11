package parallelims.api

import java.util.concurrent.Semaphore

import parallelims.process.Forker


object ParAPI {


  def unit[A](unit: => A):Par[A] = new ParImplement[A](unit)


  def map2[A,B,C](first:Par[A],second:Par[B])(f:(A,B)=>C):Par[C] = unit(f(first.get,second.get))


  def fork[A](a: => Par[A]):Par[A] = {
    val semaphore = new Semaphore(0)
    val thread = new Forker(semaphore,a)
    thread.start()
    semaphore.acquire()
    a
  }


}
