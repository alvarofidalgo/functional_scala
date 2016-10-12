package lists

import org.scalatest.FlatSpec
import org.scalatest.ShouldMatchers
import conversions.List._

class ListOperationsRecursiveTest extends FlatSpec with ShouldMatchers {

  trait ListNotEmpty {

    val list = MyList[Int](1, 2, 3)
  }

  trait Empty {

    val list = MyList[Int]()
  }

  " We want to calculate length and result " should " be List length " in new ListNotEmpty {
    val expected = 3
    list.length shouldBe expected
  }

  "We want to calculate tail and result " should " be tail of list if exist elements " in new ListNotEmpty {
    val expected = MyList[Int](2, 3)
    list.tail shouldBe expected
  }

  it should " be Nil if not have data " in new Empty {
    val expected = Nil
    list.tail shouldBe expected
  }


  "We want to calculate head of List and result " should " be None if empty List " in new Empty {
    val expected = None
    list.head shouldBe expected
  }

  it should " be Option head when exist head " in new ListNotEmpty {
    val expected = Some(1)
    list.head shouldBe expected
  }

  " We want to modify head of list and result " should " be new list with new head if We have elements " in new
      ListNotEmpty {
    val expected = MyList[Int](7, 2, 3)
    val head = 7
    list.modify(head) shouldBe expected
  }

  it should " be Nil if list is empty " in new Empty {
    val expected = Nil
    val head = 7
    list.modify(head) shouldBe expected
  }



  "we want to drop n Elements and result" should " list without first n elements" in new ListNotEmpty {
    val expected = MyList[Int](3)
    val elements = 2
    list.drop(elements) shouldBe expected
  }

  it should " be Nil if list is empty " in new Empty {
    val expected = Nil
    val elements = 2
    list.drop(elements) shouldBe expected
  }

  it should " be Nil we want delete more elements " in new ListNotEmpty {
    val expected = Nil
    val elements = 11
    list.drop(elements) shouldBe expected
  }

  "We want to drop elements and result " should " be list with head first element not match with predicate " in new
      ListNotEmpty {
    val expected = MyList[Int](3)
    val predicate: Int => Boolean = (a) => a < 3
    list.dropWhile(predicate) shouldBe expected
  }

  it should " be empty list when match predicate all elements" in new ListNotEmpty {

    val expected = Nil
    val predicate: Int => Boolean = (a) => a < 12
    list.dropWhile(predicate) shouldBe expected
  }


  it should "be Nil if list was Nil " in new Empty {
    val expected = Nil
    val predicate: Int => Boolean = (a) => a < 12
    list.dropWhile(predicate) shouldBe expected
  }


  "We want to append two list and result " should " be list with two elements when two lists hac elements " in new
      ListNotEmpty {
    val otherList = MyList[Int](4, 5, 6)
    val expected = MyList[Int](1, 2, 3, 4, 5, 6)
    list.append(otherList) shouldBe expected
  }

  it should " be second List when first list is empty " in new Empty {
    val otherList = MyList[Int](4, 5, 6)
    val expected = MyList[Int](4, 5, 6)
    list.append(otherList) shouldBe expected
  }

  it should " be Nil when two list are empty " in new Empty {

    val otherList = Nil
    val expected = Nil
    list.append(otherList) shouldBe expected
  }

  " we want to add new element in MyList and result " should " be List with new element if not empty " in new
      ListNotEmpty {
    val element = 4
    val expected = MyList(1, 2, 3, 4)
    list.add(element) shouldBe expected
  }

  it should " List with one element when list is empty " in new Empty {
    val element = 4
    val expected = MyList(4)
    list.add(element) shouldBe expected
  }

  "We want to implement init function ad result " should " be list without last element " in new ListNotEmpty {
    val expected = MyList(1, 2)
    list.init shouldBe expected
  }

  it should " empty lists when list is empty " in new Empty {
    val expected = MyList()
    list.init shouldBe expected
  }


  "We want to implement foldRight with multiply higher-order function  and result " should " be zero if empty list " +
    "" in new Empty {

    import implicits.Accumulators.initMultiplyAcc

    val expected = 0
    val multiply: (Int, Int) => Int = (a, b) => a * b
    list.foldRight[Int](0)(multiply) shouldBe expected
  }

  it should " multiply all elements if no empty list " in new ListNotEmpty {

    import implicits.Accumulators.initMultiplyAcc

    val expected = 6
    val multiply: (Int, Int) => Int = (a, b) => a * b
    list.foldRight[Int](0)(multiply) shouldBe expected
  }

  "We want to implement sum with foldRight and result " should " be zero if no elements " in new Empty {

    import implicits.Accumulators.initSumAcc

    val expected = 0
    val multiply: (Int, Int) => Int = (a, b) => a + b
    list.foldRight[Int](0)(multiply) shouldBe expected
  }

  it should " sum of all elements if we have elements " in new ListNotEmpty {

    import implicits.Accumulators.initSumAcc

    val expected = 6
    val multiply: (Int, Int) => Int = (a, b) => a + b
    list.foldRight[Int](0)(multiply) shouldBe expected
  }

  "We want to implement length with foldRight and result " should " be list length "  in new ListNotEmpty {
    val expected = 3
    list.lengthWithFoldRight shouldBe expected
  }
}
