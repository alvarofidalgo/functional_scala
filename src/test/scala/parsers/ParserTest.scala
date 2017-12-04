package parsers

import matchers.CustomMatchers
import matchers.CustomMatchers._
import org.scalatest.{FlatSpec, ShouldMatchers}
import parsers.impl.ParserImplementation


//WHEN TEST BE IN GREEN REFACTORN TEST
class ParserTest extends FlatSpec with ShouldMatchers{



  val parser = new ParserImplementation()


  trait CharEntry {
    val entry = 'c'
  }

  trait StringEntry {
    val entry = "entry"
  }

  behavior of " We like implement Parsers and result should "

  it should " be Error when  char Parser have an error " in new CharEntry{


    val expected:Either[Exception,Char] = Left(new Exception(""))
    val result:Either[Exception, Char] =  parser.run(parser.char(entry))('k'.toString)
    result shouldBe  eitherEqualTo(expected)

  }



  it should " be new char Parase when PArser not have error " in new CharEntry{
    val expected:Either[Exception,Char] = Right(entry)
    val result:Either[Exception, Char] =  parser.run(parser.char(entry))(entry.toString)
    result shouldBe  eitherEqualTo(expected)
  }


  it should " be Error when  Srtring Parser have an error " in new StringEntry{


    val expected:Either[Exception,String] = Left(new Exception(""))
    val result:Either[Exception, String] =  parser.run(parser.string(entry))("oleole")
    result shouldBe  eitherEqualTo(expected)

  }



  it should " be new char String parser when PArser not have error " in new StringEntry{
    val expected:Either[Exception,String] = Right(entry)
    val result:Either[Exception, String] =  parser.run(parser.string(entry))(entry)
    result shouldBe  eitherEqualTo(expected)
  }

  it should " be first parser when is equal to entry " in new  StringEntry{
    val expected:Either[Exception,String] = Right(entry)
    val result:Either[Exception, String] =  parser.run(parser.or(parser.string(entry),parser.string("other")))(entry)
    result shouldBe  eitherEqualTo(expected)
  }

  it should " be second parser  in error when is equal to entry " in new  StringEntry{
    val other = "other"
    val expected:Either[Exception,String] = Left(new Exception)
    val result:Either[Exception, String] =  parser.run(parser.or(parser.string(entry),parser.string(other)))("aaaa")
    result shouldBe  eitherEqualTo(expected)
  }


  it should " be second parser   when is equal to entry " in new  StringEntry{
    val other = "other"
    val expected:Either[Exception,String] =  Right(other)
    val result:Either[Exception, String] =  parser.run(parser.or(parser.string(entry),parser.string(other)))(other)
    result shouldBe  eitherEqualTo(expected)
  }

  it should " be empty list when not contains element " in new CharEntry{
    val expected:Either[Exception,List[Char]] = Right(List.empty[Char])
    val result:Either[Exception,List[Char]] = parser.run(parser.many(parser.char(entry)))("bbbb")
    result shouldBe  eitherEqualTo(expected)

  }

  it should " be list with one parser when exist one coincidence " in new CharEntry{
    val expected:Either[Exception,List[Char]] = Right(List(entry))
    val result:Either[Exception,List[Char]] = parser.run(parser.many(parser.char(entry)))(s"bb${entry}bb")
    result shouldBe  eitherEqualTo(expected)

  }

  /**
    *
    * bbbbabbb
    * b
    * (a) -> (a)
    * (ab) -> (a,b,ab)
    * (abc) -> (a,b,c,ab,bc,abc)
    */

  it should " be list with two parser when exist one coincidence " in new CharEntry{
    val expected:Either[Exception,List[Char]] = Right(List(entry,entry))
    val result:Either[Exception,List[Char]] = parser.run(parser.many(parser.char(entry)))(s"bb${entry}bb${entry}")
    result shouldBe  eitherEqualTo(expected)

  }


  it should " be empty String list when not contains element " in new StringEntry{
    val expected:Either[Exception,List[String]] = Right(List.empty[String])
    val result:Either[Exception,List[String]] = parser.run(parser.many(parser.string(entry)))("bbbb")
    result shouldBe  eitherEqualTo(expected)

  }

  it should " be list with one String parser when exist one coincidence " in new StringEntry{
    val expected:Either[Exception,List[String]] = Right(List(entry))
    val result:Either[Exception,List[String]] = parser.run(parser.many(parser.string(entry)))(s"bb${entry}bb")
    result shouldBe  eitherEqualTo(expected)

  }


}
