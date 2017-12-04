package sets

import org.scalatest.{FlatSpec, ShouldMatchers}

import sets.SetsFunctions._




class StringSetTest extends FlatSpec with ShouldMatchers{


  behavior of "We want to implement sets function and result "


  it should " be one set of three elements when have three elements and like sets of three  " in {

   val entry = List("a","b","c")
    entry.setsOf(elements= 3) shouldBe List("abc")

  }


  it should "be two sets of two adjacent  elements when have three elements and like sets of two " in {
    val entry = List("a","b","c")
    entry.setsOf(elements= 2) shouldBe List("ab","bc")
  }

  it should "be three sets of two adjacent  elements when have four elements and like sets of two " in {
    val entry = List("a","b","c","d")
    entry.setsOf(elements= 2) shouldBe List("ab","bc","cd")
  }


  it should "be two sets of three adjacent  elements when have four elements and like sets of three  " in {
    val entry = List("a","b","c","d")
    entry.setsOf(elements= 3) shouldBe List("abc","bcd")
  }


  behavior of " we want to implement function of all adjacents sets and result "

}
