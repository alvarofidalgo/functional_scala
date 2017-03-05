package streams


object InfiniteStreams {

  implicit class InfiniteStreams[A](value: A) {

    final def constant(implicit res: Streams[A] = Streams.cons[A](value, value.constant)): Streams[A] = res


    final def from: Streams[Int] = {
      Streams.cons[Int](addOne, addOne.from)
    }

    private def addOne:Int = value.asInstanceOf[Int] + 1
  }

}
