package streams

import lists.MyList
import matchers.StreamMatcher._
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
    val expected = MyList[Int](1, 2)
    Streams[Int](1, 2).toList shouldBe expected
  }

  it should " be List with String elements if Streams have String " in {

    "Streams[String](\"a\",\"b\").toList" should compile
  }

  it should " be List with two String elements if Streams have two String elements " in {

    val expected = MyList[String]("1","2")
    Streams[String]("1","2").toList shouldBe expected
  }

  " we want to implement fucntion take and result " should " be empty Stream when no have elements" in {
    val expected = Streams[Int]()
    Streams[Int]().take(1) should be (equalToStream(expected))
  }

  it should " be Stream with one element when take one and we have one element " in {
    val expected = Streams[Int](1)
    Streams[Int](1).take(1) should be (equalToStream(expected))
  }

  it should " be Stream with two elements when take two and we have two elements " in {
    val expected = Streams[Int](1,2)
    Streams[Int](1,2).take(4) should be (equalToStream(expected))
  }

  it should " be Empty Stream when have two elements but take zero " in {
    val expected = Streams[Int]()
    Streams[Int](1,2).take(0) should be (equalToStream(expected))
  }

  it should " be stream with two elements when take two and have foour " in {
    val expected = Streams[Int](1,2)
    Streams[Int](1,2,3,4).take(2) should be (equalToStream(expected))
  }


  "We want to reverse Stream and result " should " be Empty Stream when no have elements " in {
     val expected = Streams[Int]()
    Streams[Int]().reverse should be (equalToStream(expected))
  }


  it should " be Stream with one element when original Stream have one " in {
    val expected = Streams[Int](1)
    Streams[Int](1).reverse should be (equalToStream(expected))
  }


  it should " be Stream with two reverse elements when original Stream have two " in {
    val expected = Streams[Int](2,1)
    Streams[Int](1,2).reverse should be (equalToStream(expected))
  }

  " We want to implement drop function and result " should " be empty Stream when we have Empty Stream " in {
    val expected = Streams[Int]()
    Streams[Int]().drop(1) should be (equalToStream(expected))
  }

  it should " be Stream with one element when we have one element and drop zero " in {
    val expected = Streams[Int](1)
    Streams[Int](1).drop(0) should be (equalToStream(expected))
  }

  it should " be empty Stream when we have Stream with one element and drop one " in {
    val expected = Streams[Int]()
    Streams[Int](1).drop(1) should be (equalToStream(expected))
  }

  it should " be empty Stream when we have one element and whe drop three " in {
    val expected = Streams[Int]()
    Streams[Int](1).drop(3) should be (equalToStream(expected))
  }

  " We want to implement takeWhile and result " should " be empty Stream when Stream was empty" in {
    val expected = Streams[Int]()
    Streams[Int]().takeWhile((a)=> true) should be (equalToStream(expected))
  }


  it should " be all original Stream when all elements complain predicate " in {
    val expected = Streams[Int](1)
    Streams[Int](1).takeWhile((a)=> true) should be (equalToStream(expected))
  }

  it should " be Empty Stream when not exist elements that complain predicate " in {
    val expected = Streams[Int]()
    Streams[Int](1,2).takeWhile((a)=> a > 3) should be (equalToStream(expected))
  }

  " We want to implement foldRigth function and result " should " be initial value if Stream was empty " in {
    val initial = ""
    val f :(Int) => String =  (a)=>a.toString
    Streams[Int]().foldRight(initial)((a,b)=>{f(a) + b}) shouldBe initial
  }

  it should "be concatenate initial with function if no empty " in {
    val initial = ""
    val expected = "12"
    val f :(Int) => String =  (a)=>a.toString
    Streams[Int](1,2).foldRight(initial)((a,b)=>{f(a) + b}) shouldBe expected
  }

}

