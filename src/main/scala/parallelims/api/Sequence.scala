package parallelims.api

import parallelims.types.Types.Par


object Sequence {


  implicit class Sequence[A](sequence: Seq[A]){
    def parMap[B](f: A => B): Par[Seq[B]] = ???
  }

}
