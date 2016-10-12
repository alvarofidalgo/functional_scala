package conversions

import lists.ListOperationsFoldRight
import lists.MyList
import lists.ListOperationsRecursive

object List {
  implicit def recursive[A](list:MyList[A]):ListOperationsRecursive[A] = new ListOperationsRecursive[A](list)
  implicit def withFoldRight(list:MyList[Int]):ListOperationsFoldRight = new ListOperationsFoldRight(list)
}
