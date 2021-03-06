package parsers



trait Parsers[ParserErrror,Parser[+_]]{self=>



  def runParser[A](parser: Parser[A])(input:String):Either[ParserErrror,A]
  def char(char:Char):Parser[Char]
  implicit def string(s: String): Parser[String]
  def or[A](p1:Parser[A],p2:Parser[A]):Parser[A]

  implicit def asStringParser[A](a: A)(implicit f: A => Parser[String]): ParserOps[String] = ParserOps(f(a))
  def many[A](p: Parser[A]): Parser[List[A]]
  def map[A,B](a: Parser[A])(f: A => B): Parser[B]
  def map2[A,B,C](p1:Parser[A],p2:Parser[B])(f:(A,B)=>C):Parser[C]
  def succeed[A](a:A):Parser[A]
  def slice[A](parser:Parser[A]):Parser[String]
  def many1[A](p: Parser[A]): Parser[List[A]]
  def product[A,B](p1:Parser[A],p2:Parser[B]):Parser[(A,B)]

  implicit class ParserOps[A](p:Parser[A]){
    def |[B>:A](p2: Parser[B]): Parser[B] = self.or(p,p2)
    def or[B>:A](p2: => Parser[B]): Parser[B] = self.or(p,p2)
    def map[B](f:A=>B):Parser[B] = self.map(p)(f)
    def **[B](p2:Parser[B]):Parser[(A,B)] = self.product(p,p2)

  }
}

