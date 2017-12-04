package parsers.impl

import parsers.Parsers
import parsers.model.ParserTypes._

class ParserImplementation extends Parsers[Exception,Parser]{


  def run[A](parser: Parser[A])(input: String):Either[Exception,A] = parser(input)



  implicit def char(char: Char):Parser[Char] =  (c1)=> {
    char.toString match {
      case  x if (x == c1) => Right(char)
      case  _ =>  Left(new Exception())
    }
  }

  override implicit def string(str: String): Parser[String] =     (strCompare)=> {
    str match {
      case  x if (x == strCompare) => Right(str)
      case  _ =>  Left(new Exception())
    }
  }

  override def or[A](p1: Parser[A], p2: Parser[A]) = ???

  override def many[A](p: Parser[A]) = ???

  override def map[A, B](a: Parser[A])(f: A => B) = ???
}


