package monoid

trait MonoIds {


  val intAddition: MonoId[Int] = new MonoId[Int] {
      def op(a: Int, b: Int):Int = a + b

      def zero:Int = 0
  }

  val intMultiplication: MonoId[Int] = new MonoId[Int] {
     def op(a: Int, b: Int):Int = a*b

     def zero:Int = 1
  }

  val booleanOr:MonoId[Boolean] = new MonoId[Boolean] {
     def op(a: Boolean, b: Boolean): Boolean = a || b

     def zero: Boolean = false
  }


  val booleanAnd:MonoId[Boolean] = new MonoId[Boolean] {

    override def op(a: Boolean, b: Boolean): Boolean = a && b

    override def zero: Boolean = true
  }


  def optionMonoid[A]:MonoId[Option[A]] = new MonoId[Option[A]]{




    override def zero = None

    override def op(a: Option[A], b: Option[A]) = (a,b) match {
      case (None,None)=>None
      case (None,any)=>  any
      case (Some(valueA),Some(valueB)) => Some(valueA.asInstanceOf[Int] * valueB.asInstanceOf[Int]).asInstanceOf[Option[A]]
    }
  }




}
