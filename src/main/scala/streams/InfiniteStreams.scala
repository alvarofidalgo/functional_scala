package streams

object InfiniteStreams {

  implicit class InfiniteStreams[A](value: A) {

    final def constant(implicit res: Streams[A] = Streams.cons[A](value, value.constant)): Streams[A] = res

    final def from(implicit acc : Streams[Int] =  Streams.cons[Int](addOne,addOne.from)): Streams[Int] = acc


    private def addOne:Int = value.asInstanceOf[Int] + 1
  }

}
