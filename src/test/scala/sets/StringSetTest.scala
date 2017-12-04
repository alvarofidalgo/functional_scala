package sets

import org.scalatest.{FlatSpec, ShouldMatchers}

import sets.SetsFunctions._




class StringSetTest extends FlatSpec with ShouldMatchers{


  behavior of "We want to implement sets function and result "


  it should "be sets of one elements " in  {
    val entry = List("a","b","c")
    entry.setsOf(elements= 1)
  }


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


  it should "set of one element when hace only one element " in {
    val entry = List("a")
    entry.adjacentSets shouldBe List("a")
  }

 it should "set of three when have two element" in {
    val entry = List("a","b")
    entry.adjacentSets shouldBe List("ab","a","b")
  }


  it should "set of six when have three element" in {
    val entry = List("a","b","c")
    entry.adjacentSets shouldBe List("abc","ab","bc","a","b","c")
  }

}
