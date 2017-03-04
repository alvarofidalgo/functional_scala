package conversions

import lists._

object List {

  implicit def recursive[A](list: MyList[A]): ListOperationsRecursive[A] = new ListOperationsRecursive[A](list)

  implicit def withFoldRight[A](list: MyList[A]): ListOperationsFoldRight[A] = new ListOperationsFoldRight[A](list)

  implicit def withFoldLeft[A](list: MyList[A]):ListOperationsFoldLeft[A] = new ListOperationsFoldLeft(list)

 // implicit def withFlatMap[A](list:MyList[A]):ListOperationsFlatMap[A] = new ListOperationsFlatMap(list)
}
