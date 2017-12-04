package parsers

import org.scalatest.{FlatSpec, ShouldMatchers}
import parsers.impl.ParserImplementation
import matchers.CustomMatchers._


//WHEN TEST BE IN GREEN REFACTORN TEST
class ParserTest extends FlatSpec with ShouldMatchers{



  val parser = new ParserImplementation()


  behavior of " We like implement Parsers and result should "

  it should " be Error when  char Parser have an error " in{


    val expected:Either[Exception,Char] = Left(new Exception(""))
    val result:Either[Exception, Char] =  parser.run(parser.char('c'))('c')
    result shouldBe  eitherEqualTo(expected)

  }



  it should " be new char Parase when PArser not have error " in {
    val expected:Either[Exception,Char] = Right('d')
    val result:Either[Exception, Char] =  parser.run(parser.char('d'))('1')
    result shouldBe  eitherEqualTo(expected)
  }


  it should " be Error when  Srtring Parser have an error " in{


    val expected:Either[Exception,String] = Left(new Exception(""))
    val result:Either[Exception, String] =  parser.run(parser.string("oleole"))("oleole")
    result shouldBe  eitherEqualTo(expected)

  }



  it should " be new char String parser when PArser not have error " in {
    val expected:Either[Exception,String] = Right("oleole")
    val result:Either[Exception, String] =  parser.run(parser.string("oleole"))("")
    result shouldBe  eitherEqualTo(expected)
  }

}
