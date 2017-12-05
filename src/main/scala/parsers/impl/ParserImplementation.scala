package parsers.impl

import parsers.Parsers
import parsers.model.ParserTypes._

object ParserImplementation extends Parsers[Exception,Parser]{


  def runParser[A](parser: Parser[A])(input: String):Either[Exception,A] = parser(input)



  implicit def char(char: Char):Parser[Char] =  string(char.toString).map(_.charAt(0))


  implicit def string(str: String): Parser[String] =     (strCompare)=> {
    str match {
      case  x if x == strCompare => Right(str)
      case  _ =>  Left(new Exception())
    }
  }

  def or[A](p1: Parser[A], p2: Parser[A]):Parser[A] = (str)=> {
    p1(str) match  {
      case Right(a)=>Right(a)
      case Left(_) => p2(str)

    }
  }

  def many[A](p: Parser[A]): Parser[List[A]] = (str) =>{
    import sets.SetsFunctions._

    val allSets =str.toList.map(_.toString).adjacentSets
    Right(allSets.foldLeft(List.empty[A]) { (result, head) =>
      p(head.toString) match {
        case Right(a) => result ++ Seq(a)
        case Left(_) => result
      }
    })
  }

  def map[A, B](a: Parser[A])(f: A => B):Parser[B] = (str) => {

    val result:Either[Exception, A] = a(str)
    result match {
      case Right(a)=>Right(f(a))
      case Left(a)=>Left(a)
    }

  }

}


