package parsers


trait Parsers[ParserErrror,Parser[+_]]{self=>




  def run[A](parser: Parser[A])(input:String):Either[ParserErrror,A]
  def char(char:Char):Parser[Char]
  implicit def string(s: String): Parser[String]
  def or[A](p1:Parser[A],p2:Parser[A]):Parser[A]

  implicit def operators[A](p: Parser[A]) = ParserOps[A](p)
  implicit def asStringParser[A](a: A)(implicit f: A => Parser[String]): ParserOps[String] = ParserOps(f(a))
  def many[A](p: Parser[A]): Parser[List[A]]
  def map[A,B](a: Parser[A])(f: A => B): Parser[B]

  case class ParserOps[A](p:Parser[A]){
    def |[B>:A](p2: Parser[B]): Parser[B] = self.or(p,p2)
    def or[B>:A](p2: => Parser[B]): Parser[B] = self.or(p,p2)
  }


}

