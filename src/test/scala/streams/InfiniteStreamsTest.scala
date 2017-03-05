package streams

import org.scalatest.FlatSpec
import org.scalatest.ShouldMatchers

import matchers.StreamMatcher._

class InfiniteStreamsTest extends FlatSpec with ShouldMatchers{


  import streams.StreamsOperations.StreamsOperations


  trait Infinite {
    import InfiniteStreams._
    val infinite = 1.constant
  }


  " We implement function that create infinite Streams and result   " should " be we take zero elements " in new Infinite{
    val expected = Streams[Int]()
    infinite.take(0) should be (equalToStream(expected))

  }

  it should " be we take one element with same Stream " in new Infinite{
    val expected = Streams[Int](1)
    infinite.take(1) should be (equalToStream(expected))
  }

  it should " be take 10 elements in the same Stream " in new Infinite {
    val expected = Streams[Int](1,1,1,1,1,1,1,1,1,1)
    infinite.take(10) should be (equalToStream(expected))
  }
}
