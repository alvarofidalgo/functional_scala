package parsers

import org.scalatest.{FlatSpec, ShouldMatchers}
import parsers.impl.ParserImplementation
import parsers.model.ParserModel
import matchers.CustomMatchers._

class ParserTest extends FlatSpec with ShouldMatchers{



  val parser = new ParserImplementation()


  behavior of " We like implement Parser Trait and result should  "

  it should " be Error when  Parser have an error " in{


    val expected:Either[Exception,Char] = Left(new Exception(""))
    val result:Either[Exception, Char] =  parser.run(parser.char('c'))("c")
    result shouldBe  eitherEqualTo(expected)

  }



  it should " be new Paraser when PArser not have error " in {
    val expected:Either[Exception,Char] = Right('d')
    val result:Either[Exception, Char] =  parser.run(parser.char('d'))("1")
    result shouldBe  eitherEqualTo(expected)
  }

}
