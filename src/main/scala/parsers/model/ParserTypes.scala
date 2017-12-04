package parsers.model

object ParserTypes {

  type Parser[+A] = String =>Either[Exception,A]

}
