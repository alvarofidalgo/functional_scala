package parallelims.api

import java.util.concurrent.Semaphore


object ParAPI {


  def unit[A](unit: => A):Par[A] = new ParImplement[A](unit)


  def map2[A,B,C](first:Par[A],second:Par[B])(f:(A,B)=>C):Par[C] = unit(f(first.get,second.get))


  def fork[A](a: => Par[A]):Par[A] = {
    val semaphore = new Semaphore(0)
    var returned:Par[A] = null
    val thread = new Thread() {

      override def run(): Unit = {
        returned = a
        semaphore.release()
      }
    }
    thread.start()
    semaphore.acquire()
    returned
  }


}
