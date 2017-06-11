package parallelims.process

import java.util.concurrent.Semaphore

import parallelims.api.{Par, ParImplement}


object  Forker extends Thread{

  def runPar[A](semaphore:Semaphore,value:Par[A] )
            (f1:(Thread)=>Unit)
            (f2:(Par[A])=>A):Par[A] = {
    var result:A =null.asInstanceOf[A]

    f1(new Thread() {
      override def run() = {
        result = f2(value)
        semaphore.release()
      }
    })
    semaphore.acquire()
    new ParImplement[A](result)
  }


}
