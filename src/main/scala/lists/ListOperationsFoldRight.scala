package lists


import implicits.Accumulators
import conversions.List.recursive


class ListOperationsFoldRight(list:MyList[Int]) {


  def length:Int = {
    import Accumulators.initSumAcc
    list.foldRight[Int](default = 0)((a,b)=>b+1)
  }

  def multiply:Int = {
    import Accumulators.initMultiplyAcc
    list.foldRight[Int](default = 0)((a, b) => b * a)
  }

  def sum:Int = {
    import Accumulators.initSumAcc
    list.foldRight[Int](default = 0)((a,b)=>a+b)
  }


}
