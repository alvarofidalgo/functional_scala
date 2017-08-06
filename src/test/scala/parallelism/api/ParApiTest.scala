package parallelism.api

import org.scalatest.{FlatSpec, ShouldMatchers}
import parallelism.types.Types.Par



class ParApiTest extends FlatSpec with ShouldMatchers {


  import matchers.CustomMatchers._
  behavior of "We want implement map2 function and result "


  it should " be new Par with data combine " in {
    val first:Par[Int] = (execution) => execution.submit(MyCallable(callReturn = 1) )
    val second:Par[String] = (execution) => execution.submit(MyCallable(callReturn = "A") )
    val expected:Par[Double] =(execution) => execution.submit(MyCallable( callReturn = 1.toDouble + 1.toDouble))
    val f:(Int,String) => Double = (a,b) => a.toDouble + b.size.toDouble
    ParAPI(par =first).map2(second)(f) shouldBe parallelismIsEqualTo(expected)
  }




  behavior of " We want to implement map function in parAPI and result "

  it should " be new Par[Boolean] when apply f(Int)=>Boolean " in {
    val element:Par[Int] = (execution) => execution.submit(MyCallable(callReturn = 1) )
    val expected:Par[Boolean] = (execution) => execution.submit( MyCallable(callReturn = true))
    val f:(Int)=>(Boolean) = (a) => a == 1
    ParAPI(par =element).map(f) shouldBe parallelismIsEqualTo(expected)
  }


  //FIXME : Split of this class
  behavior of " We want to implement secuence function and result "

  it should " be Empty Par of List when List of Par is empty " in {
      val element:Seq[Par[Int]] = Seq.empty[Par[Int]]
      val expected:Par[Seq[Int]]  = (execution) => execution.submit( MyCallable( callReturn = Seq.empty[Int]))
      val ps:Par[Int] = (execution) => execution.submit(MyCallable(callReturn = 1) )
      ParAPI(par =ps) sequence element shouldBe   parallelismIsEqualTo(expected)
  }


  it should " be non Empty par of List when List of Par is not empty " in {
    val element:Seq[Par[Int]] = Seq((execution) => execution.submit( MyCallable( callReturn = 1)))
    val expected:Par[Seq[Int]]  = (execution) => execution.submit( MyCallable( callReturn = Seq(1)))
    val ps:Par[Int] = (execution) => execution.submit(MyCallable(callReturn = 1) )
    ParAPI(par =ps) sequence element shouldBe parallelismIsEqualTo(expected)
  }

}
