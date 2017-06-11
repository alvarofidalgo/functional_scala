package parallelims.process

import java.util.concurrent.Semaphore

import parallelims.api.{Par, ParImplement}


case class Forker[A] (var returned:Par[A])extends Thread{

def runPar(semaphore:Semaphore):Par[A] = {
  new Thread() {
    override def run() = {
      returned = new ParImplement[A](returned.get)
      semaphore.release()
    }
  }.start()
  returned
}


}
