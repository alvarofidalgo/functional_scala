package parallelism.api


trait Callable [A]{

    def call :A
}

case class MyCallable[A] ( callReturn:A) extends Callable[A] {

    override def call: A = callReturn
}
