package lists

import conversions.List.recursive
import org.junit.runner.RunWith
import org.scalatest.FlatSpec
import org.scalatest.ShouldMatchers
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class ListOperationsRecursiveTest extends FlatSpec with ShouldMatchers {

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


  " We want to implement foldright and result " should " acc value if list is empty " in new Empty {
    val expected = 0
    val multiply: (Int, Int) => Int = (a, b) => a * b
    list.foldRight[Int](acc = 0)(multiply) shouldBe expected
  }


  it should " result of function over all elements if list not empty  " in new ListNotEmpty {

    val expected = 6
    val multiply: (Int, Int) => Int = (a, b) => a * b
    list.foldRight[Int](acc = 1)(multiply) shouldBe expected
  }



  " We want to implement foldLeft and result  " should " be acc value if list is empty " in new Empty {

    val expected = 0
    list.foldLeft[Int](acc = 0)((a, b) => a * b) shouldBe expected
  }

  it should " be result of function over all elements  " in new ListNotEmpty {

    val expected = 6
    list.foldLeft[Int](acc = 1)((a, b) => a * b) shouldBe expected
  }

  "We want to reverse list and result " should " be empty list if is empty " in new Empty {
    val expected = MyList()
    list.reverse shouldBe expected
  }

  it should "reverse list if not empty " in new ListNotEmpty {
    val expected = MyList(3, 2, 1)
    list.reverse shouldBe expected
  }

  "We want to flatten list and result " should " be empty List if list is empty " in new Empty {
    val expected = Nil
    list.flatten shouldBe expected
  }

  it should " be plain list  " in new ListOfLists {
    val expected = MyList(1, 2, 3)
    list.flatten shouldBe expected
  }

  it should "be plain list if list is plain " in new ListNotEmpty {
    val expected = MyList(1, 2, 3)
    list.flatten shouldBe expected
  }

  " We want to implement map function and result " should " be new list with sum each element " in new ListNotEmpty {
    val expected = MyList[Int](2, 3, 4)
    list.map((a) => a + 1) shouldBe expected
  }

  it should " be new list with string value " in new ListNotEmpty {
    val expected = MyList[String]("1", "2", "3")
    list.map[String]((a) => a.toString) shouldBe expected
  }

  " We want to implement function filter and result " should " be list without elements that match with function " in new ListNotEmpty {
    val expected = MyList(1, 3)
    list.filter((a) => a == 2) shouldBe expected
  }

  it should " be empty list if match all elements " in new ListNotEmpty {
    val expected = MyList()
    list.filter((a) => a < 4) shouldBe expected
  }

  "We want to implement flatMap and reult " should " be flatten list transformed by function " in new ListNotEmpty {
    val expected = MyList(1, 1, 2, 2, 3, 3)
    list.flatMap((a) => MyList(a, a)) shouldBe expected
  }

  "We want to implement zipWith function and result " should " be sum of index elements" in new ListNotEmpty {
    val other = MyList[Int](4,5,6)
    val expected = MyList[Int](5,7,9)
    list.zipWith[Int,Int](other,(a,b)=> a + b ) shouldBe expected
  }

  it should "be concatenate elements " in new  ListNotEmpty {
    val other = MyList[String]("a","b","c")
    val expected = MyList[String]("1a","2b","3c")
    list.zipWith[String,String](other,(a,b)=> a.toString.concat(b)  ) shouldBe expected
  }
}
