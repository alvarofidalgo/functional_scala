package conversions

import lists._

object List {

  implicit def recursive[A](list: MyList[A]): ListOperationsRecursive[A] = new ListOperationsRecursive[A](list)

}
