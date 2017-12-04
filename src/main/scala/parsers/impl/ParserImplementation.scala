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

  def many[A](p: Parser[A]): Parser[List[A]] = (sac) =>{

  /*  def loop(str:List[Char])(result:List[A]= List.empty[A],partial:String):List[A] = {
      (str,p(partial)) match {
        case (head::tail,Right(a)) => p(head.toString)  match  {
                        case Right(a)=>loop(tail)(result++Seq(a),partial)
                        case Left(_) => loop(tail)(result,partial)
                    }
        case (Nil,null) => result
      }
    }*/
  def loop(str:List[Char])(result:List[A]= List.empty[A],partial:String=""):List[A] = {
    (str, p(partial)) match {
      case (_ :: tail, Right(a)) =>
        loop(tail)(result ++ List(a), "")

      case (head :: tail, Left(a)) =>
        loop(tail)(result, partial.concat(head.toString))
      case (Nil, _) =>
        result
    }
  }
    val z:List[A] = loop(sac.toList)()
    Right( z)

  }

  def map[A, B](a: Parser[A])(f: A => B) = ???
}


