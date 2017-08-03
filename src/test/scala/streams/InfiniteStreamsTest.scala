package streams

import org.scalatest.FlatSpec
import org.scalatest.ShouldMatchers
import matchers.CustomMatchers._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class InfiniteStreamsTest extends FlatSpec with ShouldMatchers {

  import streams.StreamsOperations.StreamsOperations

  trait Infinite {

    import InfiniteStreams._

    val infinite = 1.constant
  }

  trait From {

    import InfiniteStreams._

    val from = 2.from
  }

  " We implement function that create infinite Streams and result   " should " be we take n elements " in new Infinite {
    val expected = Streams[Int](1, 1, 1, 1, 1, 1, 1, 1, 1, 1)
    infinite.take(10) should be(equalToStream(expected))
  }

  " We want to implement from function and result " should "be a infinite Stream from with take function " in new From {
    val expected = Streams[Int](3,4,5,6)
    from.take(4) should be(equalToStream(expected))
  }

}
