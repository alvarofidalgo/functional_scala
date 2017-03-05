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
    final def take(elements: Int)(implicit result: Streams[A] = Empty): Streams[A] = (elements, streams) match {
      case (_, Empty) => result.reverse
      case (0, _) => result.reverse
      case (_, InitStreams(head, tail)) => tail().take(elements - 1)(InitStreams(head, () => result))
    }

    @tailrec
    final def reverse(implicit result: () => Streams[A] = () => Empty): Streams[A] = streams match {
      case Empty => result()
      case InitStreams(head, tail) => tail().reverse(() => InitStreams(head, result))
    }

    @tailrec
    final def drop(elements: Int): Streams[A] = (elements, streams) match {
      case (_, Empty) => streams
      case (0, _) => streams
      case (_, InitStreams(head, tail)) => tail().drop(elements - 1)
    }

    @tailrec
    final def takeWhile(f: (A) => Boolean)
                       (implicit result: Streams[A] = Empty,
                        f2: ((A) => Boolean) => Option[Boolean] = (f) => None): Streams[A] = (f2(f), streams)
    match {
      case (_, Empty) => result
      case (None, InitStreams(head, tail)) => takeWhile(f)(result, (f) => Some(f(head())))
      case (Some(true), InitStreams(head, tail)) => tail().takeWhile(f)(InitStreams(head, () => result), (f) => Some(f(head())))
      case (Some(false), InitStreams(head, tail)) => tail().takeWhile(f)(result, (f) => Some(f(head())))
    }

    @tailrec
    final def foldRight[B](result: => B)(f: (A, => B) => B): B = streams.reverse match {
      case InitStreams(head, tail) => tail().foldRight(f(head(), result))(f)
      case _ => result
    }

    final def forAll(predicate: (A) => Boolean): Boolean = streams match {
      case Empty => false
      case _ => streams.foldRight(result = true)((value, result) => predicate(value) && result)
    }

    def map[B](transform: (A) => B): Streams[B] =
      streams.foldRight(result = Streams[B]())(
        (value, result) =>
          InitStreams(() => transform(value), () => result)
      )
  }

}
