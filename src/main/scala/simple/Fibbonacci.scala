package simple

import scala.annotation.tailrec

object Fibbonacci {


  def fib(position: Int): Int = {

    @tailrec
    def calculate(position: Int, acc: Int,result:Int): Int = position match  {
      case 0 => acc
      case _=>  calculate(position-1,result,acc+result)
    }
    calculate(position, 0,1)
  }
}
