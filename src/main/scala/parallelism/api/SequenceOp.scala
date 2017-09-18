package parallelism.api

import parallelism.types.Types._
import parallelism.api.Sequence._

object SequenceOp {

  implicit class SequenceOperations [A](entrySequence: Seq[A]){


    def parMap[B](f: A => B): Par[Seq[B]] = {
      import Asynchronous._
      val list = entrySequence.map((value) => value.asyncF(f))
      sequence(list)
    }


    def parFilter(f: A => Boolean): Par[Seq[A]] =  {
      import Asynchronous._
      val list1 = entrySequence.foldLeft(Seq.empty[Par[A]]) {
        (res,head) => f(head) match {
          case true => res ++ Seq(head.asyncF((a)=>a))
          case _ => res
        }

      }
      sequence(list1)
    }

  }

}
