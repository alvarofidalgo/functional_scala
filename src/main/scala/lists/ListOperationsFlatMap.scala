package lists



object ListOperationsFlatMap {

  implicit class ListOperationsFlatMap[A](list: MyList[A]) {
    import lists.ListOperationsRecursive._

    def filter(f: (A) => Boolean): MyList[A] = {
      list.flatMap((element) => f(element) match {
        case true => Nil
        case false => MyList(element)
      })
    }
  }

}
