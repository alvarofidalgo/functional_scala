package parsers.impl

import parsers.Parsers
import parsers.model.ParserModel

class ParserImplementation extends Parsers[Exception,ParserModel]{


  def run[A](parser: ParserModel[A])(input: String):Either[Exception,A] = {
    if (parser.isEqualTo(input.charAt(0)))
    Left(new Exception())
    else
      Right(parser.element)
  }

  def char(char: Char) =  ParserModel[Char](char)
}
