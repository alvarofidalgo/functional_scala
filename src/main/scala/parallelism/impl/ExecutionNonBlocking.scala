package parallelism.impl


class ExecutionNonBlocking[A](value:A) {

  def submit(f:A=>Unit):Unit = f(value)

}
