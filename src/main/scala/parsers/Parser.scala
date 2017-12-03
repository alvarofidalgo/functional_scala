package parsers

trait Parser[ParserErrror,Parser[+_]]{


  def run[A](parser: Parser[A])(input:String):Either[ParserErrror,A]


  def char(char:Char):Parser[Char]



}
