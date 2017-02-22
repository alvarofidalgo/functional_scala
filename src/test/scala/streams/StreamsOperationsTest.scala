package streams

import lists.MyList
import org.scalatest.FlatSpec
import org.scalatest.ShouldMatchers

class StreamsOperationsTest extends FlatSpec with ShouldMatchers {

  import streams.StreamsOperations.StreamsOperations
  import lists.Nil

  " We want to transform Streams toList and result " should " be emptyList if no elements " in {

    val expected = Nil
    Streams[Int]().toList shouldBe expected
  }

  it should " be List with one Integer elements if streams have one Integer element" in {
    val expected = MyList[Int](1)
    Streams[Int](1).toList shouldBe expected
  }

  it should " be List with two Integer elements if Streams have two integer elements " in {
    val expected = MyList[Int](1,2)
    Streams[Int](1,2).toList shouldBe expected
  }

  it should " be List with String elements if Streams have String " in {

    "Streams[String](\"a\",\"b\").toList" should compile
  }

  it should " be List with two String elements if Streams have two String elements " in {
    val expected = MyList[String]("1","2")
    Streams[String]("1","2").toList shouldBe expected
  }
}

