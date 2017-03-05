package streams


object InfiniteStreams {

  implicit class InfiniteStreams[A](value: A) {

    final def constant(implicit res: Streams[A] = Streams.cons[A](value, value.constant)): Streams[A] = res


    final def from: Streams[Int] = {
      val newValue = value.asInstanceOf[Int]
      Streams.cons[Int](newValue+1, (newValue+1).from)
    }
  }

}
