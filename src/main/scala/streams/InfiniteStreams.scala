package streams

object InfiniteStreams {



  def constant[A](value:A):Streams[A] = Streams.cons[A](value, constant[A](value))




}
