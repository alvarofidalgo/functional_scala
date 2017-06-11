package parallelims.process

import java.util.concurrent.Semaphore

import parallelims.api.{Par, ParImplement}


case class Forker[A] (var returned:Par[A])extends Thread{

  def runPar(semaphore:Semaphore)(f:Par[A]=>A):Par[A] = {
    var result:A =null.asInstanceOf[A]
    new Thread() {
      override def run() = {
        result = f(returned)
        semaphore.release()
      }
    }.start()
    semaphore.acquire()
    new ParImplement[A](result)
  }


}
