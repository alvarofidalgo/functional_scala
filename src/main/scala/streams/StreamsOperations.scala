package streams

import scala.annotation.tailrec

import lists.Init
import lists.MyList



object StreamsOperations {

  import lists.Nil
  import conversions.List.recursive

  implicit class StreamsOperations[A](streams: Streams[A]) {

    @tailrec
    final def toList(implicit acc:MyList[A]=Nil,
                              tail:Streams[A]=streams): MyList[A] = tail match {
      case Empty => acc.reverse(Nil)
      case InitStreams(fHead,fTail) =>
        val myList = Init[A](fHead(),acc)
        toList(myList,fTail())

    }

    final def take(elements:Int)(implicit result:Streams[A]=Empty):Streams[A] = streams match {
      case Empty =>  result
      case InitStreams(fHead,fTail)  => if (elements==0)
                                            result
                                        else new StreamsOperations[A](fTail()).take(elements-1)(InitStreams(fHead,()
      =>result))

    }


  }
}
