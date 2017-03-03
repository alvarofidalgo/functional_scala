package streams

import scala.annotation.tailrec

import lists.Init
import lists.MyList



object StreamsOperations {

  import lists.Nil


  implicit class StreamsOperations[A](streams: Streams[A]) {

    import conversions.List.withFoldLeft


    @tailrec
    final def toList(implicit result: MyList[A] = Nil): MyList[A] = streams match {
      case Empty => result
      case InitStreams(head, tail) =>
        new StreamsOperations(tail()).toList(result.append(MyList(head())))
    }



    @tailrec
    final def take(elements:Int)(implicit result:Streams[A]=Empty):Streams[A] = (elements,streams) match {
      case (_,Empty) =>  result
      case (0,InitStreams(fHead,fTail))  => result
      case (_,InitStreams(fHead,fTail)) =>new StreamsOperations[A](fTail()).take(elements-1)(InitStreams(fHead,() =>result))

    }


  }
}
