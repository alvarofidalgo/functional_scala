package parallelims.api

import java.util.concurrent.Semaphore

import parallelims.process.Forker


object ParAPI {


  def unit[A](unit: => A):Future[A] = new ParImplement[A](unit)


  def map2[A,B,C](first:Future[A],second:Future[B])(f:(A,B)=>C):Future[C] = unit(f(first.get,second.get))


  def fork[A](a: => Future[A]):Future[A] = Forker.runPar(new Semaphore(0),a)
           {
              (t)=> t.start()
            }{
              (par) => par.get
            }



}
