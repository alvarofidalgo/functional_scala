package parsers.impl

import parsers.Parsers
import parsers.model.ParserTypes._

import scala.collection.immutable

class ParserImplementation extends Parsers[Exception,Parser]{


  def run[A](parser: Parser[A])(input: String):Either[Exception,A] = parser(input)



  implicit def char(char: Char):Parser[Char] =  (c1)=> {
    char.toString match {
      case  x if (x == c1) => Right(char)
      case  _ =>  Left(new Exception())
    }
  }

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

  def map[A, B](a: Parser[A])(f: A => B) = ???
}


