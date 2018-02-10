package monoid

import org.scalatest.{FlatSpec, ShouldMatchers}

class FoldableTest extends FlatSpec with ShouldMatchers{

  trait EntryData {
    val fTransform:(Int)=>String = (entry) => entry.toString
    val monoId = new MonoId[String] {
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

}
