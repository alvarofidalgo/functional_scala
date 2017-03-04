package lists

import conversions.List.recursive

object ListOperationsFlatMap {

  implicit class ListOperationsFlatMap[A](list: MyList[A]) {

    def filter(f: (A) => Boolean): MyList[A] = {
      list.flatMap((element) => f(element) match {
        case true => Nil
        case false => MyList(element)
      })
    }
  }

}
