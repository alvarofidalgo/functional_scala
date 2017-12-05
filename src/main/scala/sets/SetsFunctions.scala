package sets


import scala.annotation.tailrec


object SetsFunctions {


  implicit  class Sets (cad:List[String]) {


    @tailrec
    final def setsOf(elements:Int)
                    (implicit result:List[String] = List.empty[String],
                              partial:String="",
                              next:List[String] =  List.empty[String]):List[String] = (elements,cad,partial.length + 1) match  {
      case (1,_,_)=>cad
      case (_,head::tail,x) if x==1 => new Sets(tail).setsOf(elements)(result,s"$partial$head",tail)
      case (_,head::tail,x) if x < elements => new Sets(tail).setsOf(elements)(result,s"$partial$head",next)
      case (_,head::_,x) if x == elements => new Sets(next).setsOf(elements)(result ++ List(s"$partial$head"),"",next)
      case (_,Nil,x) if x > elements => result ++ List(partial)
      case (_,_,_) => result
    }


    @tailrec
    final def adjacentSets(implicit elements:Int = cad.length,result:List[String]=List.empty[String] ):List[String] =elements match {
      case 0 =>
        result
      case _ =>
        adjacentSets(elements-1,result ++ setsOf(elements)( List.empty[String],"",List.empty[String]) )

    }
  }

}
