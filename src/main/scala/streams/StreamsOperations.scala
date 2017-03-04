package streams

import scala.annotation.tailrec

import lists.MyList

object StreamsOperations {

  import lists.Nil

  implicit class StreamsOperations[A](streams: Streams[A]) {

    import lists.ListOperationsRecursive._

    @tailrec
    final def toList(implicit result: MyList[A] = Nil): MyList[A] = streams match {
      case Empty => result
      case InitStreams(head, tail) => tail().toList(result.append(MyList(head())))
    }

    @tailrec
    final def take(elements: Int)(implicit result: Streams[A] = Empty): Streams[A] = (elements,streams) match {
      case (_,Empty) => result.reverse
      case (0,_) =>  result.reverse
      case (_,InitStreams(head, tail)) => tail().take(elements-1)(InitStreams(head, () => result))
    }

    @tailrec
    final def reverse(implicit result:()=> Streams[A] =()=> Empty):Streams[A] = streams match {
      case Empty=>result()
      case InitStreams(head, tail) => tail().reverse(()=>InitStreams(head, result))
    }

    @tailrec
    final def drop(elements:Int):Streams[A] = (elements,streams) match {
      case (_,Empty)=>streams
      case (0,_)=>streams
      case  (_,InitStreams(head, tail)) => tail().drop(elements-1)
    }
  }

}
