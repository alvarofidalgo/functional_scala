package parsers.impl

import parsers.Parsers
import parsers.model.ParserModel

class ParserImplementation extends Parsers[Exception,ParserModel]{


  def run[A](parser: ParserModel[A])(input: A):Either[Exception,A] = {
    if (parser.isEqualTo(input))
      Right(parser.element)
    else
      Left(new Exception())

  }

  implicit def char(char: Char) =  ParserModel[Char](char)

  override def or[A](p1: ParserModel[A], p2: ParserModel[A]): ParserModel[A] = ???

  implicit def string(s: String): ParserModel[String] = ParserModel[String](s)

  override def many[A](p: ParserModel[A]): ParserModel[List[A]] = ???

  override def map[A, B](a: ParserModel[A])(f: A => B): ParserModel[B] = ???
}


