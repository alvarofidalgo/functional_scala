package parallelims.process

import java.util.concurrent.Semaphore

import parallelims.api.{Par, ParImplement}


case class Forker[A] (semaphore:Semaphore,var returned:Par[A])extends Thread{


  override def run() = {
    returned = new ParImplement[A](returned.get)
    semaphore.release()
  }

}
