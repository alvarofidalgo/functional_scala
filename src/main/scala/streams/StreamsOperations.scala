package streams

import scala.annotation.tailrec

import lists.Init
import lists.MyList



object StreamsOperations {

  import lists.Nil
  import conversions.List.recursive

  implicit class StreamsOperations[A](streams: Streams[A]) {

    @tailrec
    implicit final def toList(implicit acc:MyList[A]=Nil,
                              tail:Streams[A]=streams): MyList[A] = tail match {
      case Empty => acc.reverse(Nil)
      case InitStreams(fHead,fTail) =>
        val myList = Init[A](fHead(),acc)
        toList(myList,fTail())

    }
  }
}
