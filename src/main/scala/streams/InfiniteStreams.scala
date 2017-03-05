package streams

object InfiniteStreams {


  implicit class InfiniteStreams[A](value:A) {

    def constant: Streams[A] = Streams.cons[A](value, value.constant)
  }




}
