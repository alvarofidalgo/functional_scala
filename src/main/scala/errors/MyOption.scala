package errors

import errors.{MyOption=>OptionNew}




case class Option[A](get:A) extends OptionNew[A]{

   def map[B](f: (A) => B): OptionNew[B] = ???
}
