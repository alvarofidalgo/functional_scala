package conversions

import lists._

object List {

  implicit def recursive[A](list: MyList[A]): ListOperationsRecursive[A] = new ListOperationsRecursive[A](list)

  implicit def withFoldRight[A](list: MyList[A]): ListOperationsFoldRight[A] = new ListOperationsFoldRight[A](list)
  
}
