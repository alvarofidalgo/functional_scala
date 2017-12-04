package sets

import scala.annotation.tailrec


object SetsFunctions {


  implicit class Sets (cad:List[String]) {

    @tailrec
    final def setsOf(elements:Int)
                    (implicit result:List[String] = List.empty[String],
                              partial:String="",
                              next:List[String] =  List.empty[String]):List[String] = (cad,partial.length + 1) match  {

      case (head::tail,x) if x==1 => new Sets(tail).setsOf(elements)(result,s"$partial$head",tail)
      case (head::tail,x) if x < elements => new Sets(tail).setsOf(elements)(result,s"$partial$head",next)
      case (head::_,x) if x == elements =>  new Sets(next).setsOf(elements)(result ++ List(s"$partial$head"),"",next)
      case (Nil,_) => result
    }
  }

}
