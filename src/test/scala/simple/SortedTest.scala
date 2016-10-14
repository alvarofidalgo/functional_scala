package simple

import org.junit.runner.RunWith
import org.scalatest.FlatSpec
import org.scalatest.ShouldMatchers
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class SortedTest extends FlatSpec with ShouldMatchers{


  " We want to know if  array by condition and result " should " true if is Integer list sorted in higth order " in {
    val array = Array[Int](1,2)
    val expeted = true
    Sorted.isSorted[Int](array,(first,second)=>first<second) shouldBe expeted
  }

  it should " be false if is Integer and not sort in higth order " in {
    val array = Array[Int](3,2)
    val expeted = false
    Sorted.isSorted[Int](array,(first,second)=>first<second) shouldBe expeted
  }

  it should " be false when is Integer list andlast element not sorted " in {
    List(1,2,3)
    val array = Array[Int](1,2,1)
    val expeted = false
    Sorted.isSorted[Int](array,(first,second)=>first<second) shouldBe expeted
  }

  it should " be false when is Integer list andlast element is sorted " in {
    val array = Array[Int](1,2,3)
    val expeted = true
    Sorted.isSorted[Int](array,(first,second)=>first<second) shouldBe expeted
  }
}
