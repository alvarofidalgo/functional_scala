package streams

object InfiniteStreams {

  implicit class InfiniteStreams[A](value: A) {

    final def constant(implicit res: Streams[A] = Streams.cons[A](value, value.constant)): Streams[A] = res
  }

}
