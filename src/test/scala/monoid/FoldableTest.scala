package monoid

import org.scalatest.{FlatSpec, ShouldMatchers}

class FoldableTest extends FlatSpec with ShouldMatchers{

  trait EntryData {
    val fTransform:(Int)=>String = (entry) => entry.toString
    implicit val monoId = new MonoId[String] {
      override def op(a: String, b: String): String = s"$a$b"

      override def zero: String = ""
    }
  }

  behavior of " We want to implement foldmap funtion and result "



  it should " be empty Striung cad when no have elements  " in new Foldable with EntryData{

    val entry = Seq.empty[Int]
    foldMap[Int,String](entry,monoId)(fTransform) shouldBe ""

  }


  it should " be numbers concatanation when exist elements "  in new Foldable with EntryData{

    val entry = Seq(1,23,45)
    foldMap[Int,String](entry,monoId)(fTransform) shouldBe "12345"

  }



  behavior of " We want to implement folLeft function and result "


  it should "Be initial value when list no have data " in new Foldable with EntryData{
     val entry = Seq.empty[Int]
     val result =foldLeft[Int,String](entry,"") {
       (result,head) => result ++ head.toString
     }
     result shouldBe ""

  }

  it should " be combinel value when list have one data " in new Foldable with EntryData{
    val entry = Seq(1)
    val result =foldLeft[Int,String](entry,"") {
      (result,head) => result ++ head.toString
    }
    result shouldBe "1"
  }

  it should " be combine value when list have two data " in new Foldable with EntryData{
    val entry = Seq(1,20)
    val result =foldLeft[Int,String](entry,"") {
      (result,head) => result ++ head.toString
    }
    result shouldBe "120"
  }

}
