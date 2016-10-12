package conversions

//import lists.ListWithFoldImplementation
import lists.MyList
import lists.ListOperationsRecursive

object List {
  implicit def newList[A](list:MyList[A]):ListOperationsRecursive[A] = new ListOperationsRecursive[A](list)
 // implicit def withFold[A](list:MyList[A]):ListWithFoldImplementation[A] = new ListWithFoldImplementation[A](list)
}
