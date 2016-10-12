package lists


trait ListNotEmpty {

  val list = MyList[Int](1, 2, 3)
}

trait Empty {

  val list = MyList[Int]()
}

trait ListOfLists {
  val list:MyList[MyList[Int]] = MyList(MyList(1,2),MyList(3))
}
