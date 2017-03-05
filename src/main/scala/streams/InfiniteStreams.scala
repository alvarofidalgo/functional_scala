package streams

object InfiniteStreams {

  def constant[Int](value:Int):Streams[Int] = Streams.cons[Int](value, constant[Int](value))




}
