package parsers

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

}
