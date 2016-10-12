package lists


trait ListNotEmpty {

  val list = MyList[Int](1, 2, 3)
}

trait Empty {

  val list = MyList[Int]()
}
