package parsers

import matchers.CustomMatchers._
import org.scalatest.{FlatSpec, ShouldMatchers}

class ParserTest extends FlatSpec with ShouldMatchers{

  import parsers.impl.ParserImplementation._

  trait CharEntry {
    val entry = 'c'
  }

  trait StringEntry {
    val entry = "entry"
  }

  behavior of " We like implement Parsers and result should "

  it should " be Error when  char Parser have an error " in new CharEntry{


    val expected:Either[Exception,Char] = Left(new Exception(""))
    val result:Either[Exception, Char] =  runParser(char(entry))('k'.toString)
    result shouldBe  eitherEqualTo(expected)

  }



  it should " be new char Parase when PArser not have error " in new CharEntry{
    val expected:Either[Exception,Char] = Right(entry)
    val result:Either[Exception, Char] =  runParser(char(entry))(entry.toString)
    result shouldBe  eitherEqualTo(expected)
  }

  behavior of " We like implement string function"




  it should " be Error when  Srtring Parser have an error " in new StringEntry{


    val expected:Either[Exception,String] = Left(new Exception(""))
    val result:Either[Exception, String] =  runParser(string(entry))("oleole")
    result shouldBe  eitherEqualTo(expected)

  }

  it should " be Rigth with empty cad when entry is empty " in new StringEntry{
    val expected:Either[Exception,String] = Right("")
    val result:Either[Exception, String] =  runParser(string(""))(entry)
    result shouldBe  eitherEqualTo(expected)
  }



  it should " be new char String parser when PArser not have error " in new StringEntry{
    val expected:Either[Exception,String] = Right(entry)
    val result:Either[Exception, String] =  runParser(string(entry))(entry)
    result shouldBe  eitherEqualTo(expected)
  }


  behavior of " We like impolement or function "

  it should " be first parser when is equal to entry " in new  StringEntry{
    val expected:Either[Exception,String] = Right(entry)
    val result:Either[Exception, String] =  runParser(or(string(entry),string("other")))(entry)
    result shouldBe  eitherEqualTo(expected)
  }

  it should " be second parser  in error when is equal to entry " in new  StringEntry{
    val other = "other"
    val expected:Either[Exception,String] = Left(new Exception)
    val result:Either[Exception, String] = runParser(or(string(entry),string(other)))("aaaa")
    result shouldBe  eitherEqualTo(expected)
  }


  it should " be second parser   when is equal to entry " in new  StringEntry{
    val other = "other"
    val expected:Either[Exception,String] =  Right(other)
    val result:Either[Exception, String] =  runParser(or(string(entry),string(other)))(other)
    result shouldBe  eitherEqualTo(expected)
  }

  behavior of " we like implment many function and result "


  it should " be empty list when not contains element " in new CharEntry{
    val expected:Either[Exception,List[Char]] = Right(List.empty[Char])
    val result:Either[Exception,List[Char]] = runParser(many(char(entry)))("bbbb")
    result shouldBe  eitherEqualTo(expected)

  }

  it should " be list with one parser when exist one coincidence " in new CharEntry{
    val expected:Either[Exception,List[Char]] = Right(List(entry))
    val result:Either[Exception,List[Char]] = runParser(many(char(entry)))(s"bb${entry}bb")
    result shouldBe  eitherEqualTo(expected)

  }


  it should " be list with two parser when exist one coincidence " in new CharEntry{
    val expected:Either[Exception,List[Char]] = Right(List(entry,entry))
    val result:Either[Exception,List[Char]] = runParser(many(char(entry)))(s"bb${entry}bb${entry}")
    result shouldBe  eitherEqualTo(expected)

  }


  it should " be empty String list when not contains element " in new StringEntry{
    val expected:Either[Exception,List[String]] = Right(List.empty[String])
    val result:Either[Exception,List[String]] = runParser(many(string(entry)))("bbbb")
    result shouldBe  eitherEqualTo(expected)

  }

  it should " be list with one String parser when exist one coincidence " in new StringEntry{
    val expected:Either[Exception,List[String]] = Right(List(entry))
    val result:Either[Exception,List[String]] = runParser(many(string(entry)))(s"bb${entry}bb")
    result shouldBe  eitherEqualTo(expected)

  }




  behavior of " we like implement map function and result "


  it should " be String parser when Apply this function " in new CharEntry {
    val expected:Either[Exception,String] = Right(entry.toString)

    val result:Either[Exception,String] = runParser( map(char(entry))((a)=> a.toString) )(entry.toString)
    result shouldBe  eitherEqualTo(expected)
  }

  it should " be String parser with error when exist error " in new CharEntry {

    val expected:Either[Exception,String] = Left(new Exception)
    val result:Either[Exception,String] = runParser( map(char(entry))((a)=> a.toString) )(s"bbbb")
    result shouldBe  eitherEqualTo(expected)
  }


  behavior of " We like implement succeed function and result "


  it should " be new parser of type " in  new CharEntry{
    val expected:Either[Exception,Char] = Right(entry)
    val result:Either[Exception, Char] =  runParser(succeed(entry))(entry.toString)
    result shouldBe  eitherEqualTo(expected)
  }



  behavior of " we like to implement slice combinator and result "


  it should " result many in one result " in  new StringEntry{
    val expected:Either[Exception,String] = Right(entry)
    val result:Either[Exception,String] = runParser(slice(many(string(entry))))(s"bb${entry}bb")
    result shouldBe  eitherEqualTo(expected)
  }


  it should " be one String when is one String " in new StringEntry{
    val expected:Either[Exception,String] = Right(entry)
    val result:Either[Exception, String] =  runParser(slice(string(entry)))(entry)
    result shouldBe  eitherEqualTo(expected)
  }



  behavior of " We want to implement Parser that recognize 1 or many occurence and result "


  it should " be Error when not contains element " in new CharEntry{
    val expected:Either[Exception,List[Char]] = Left(new Exception)
    val result:Either[Exception,List[Char]] = runParser(many1(char(entry)))("bbbb")
    result shouldBe  eitherEqualTo(expected)
  }





}
