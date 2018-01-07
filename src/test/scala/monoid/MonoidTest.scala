package monoid

import org.scalatest.{FlatSpec, ShouldMatchers}

class MonoidTest extends FlatSpec with ShouldMatchers with MonoIds {



  behavior of " We like implement a addition Monoid  Monoid "


  it should " Be 3 when ope over 2 and 1 in integer MonoidADD " in {
    intAddition.zero shouldBe 0
    intAddition.op(2,1) shouldBe 3

  }

  behavior of " We like implement an int multiplication Monoid and result "

  it should " be 6 when ope over 2 and 3 in Integer multi Monoid " in  {
    intMultiplication.zero shouldBe 1
    intMultiplication.op(2,3) shouldBe 6
  }


  behavior of " We like booleanOr mnonboid and result should "


  it should " be true when ope over true or false " in {
    booleanOr.zero shouldBe false
    booleanOr.op(true,false) shouldBe true
  }


  it should " be false when ope over false or false " in {
    booleanOr.zero shouldBe false
    booleanOr.op(false,false) shouldBe false
  }

  it should " be true when ope over true or true " in {
    booleanOr.zero shouldBe false
    booleanOr.op(true,true) shouldBe true
  }


  behavior of " We like implement booleanAnd and result should "


  it should " be false when apply or over true and false" in {
    booleanAnd.zero shouldBe true
    booleanAnd.op(true,false) shouldBe false
  }

  it should " be false when apply or over false and false" in {
    booleanAnd.zero shouldBe true
    booleanAnd.op(false,false) shouldBe false
  }

  it should " be true when apply or over true and false" in {
    booleanAnd.zero shouldBe true
    booleanAnd.op(true,true) shouldBe true
  }


  behavior of " We like implement an Option monoid and result "

  it should " be None when two elements are NONE " in {
    optionMonoid.zero shouldBe None
    optionMonoid.op(None,None) shouldBe None
  }

  it should " be value of first element when first have value and second was none " in {
    optionMonoid.op(None,Some(1)) shouldBe Some(1)
  }

}
