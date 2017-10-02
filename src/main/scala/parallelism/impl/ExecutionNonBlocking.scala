package parallelism.impl


class ExecutionNonBlocking[A](value:A) {

  def submit(f:A=>Unit):Unit = {
    new Thread(new Runnable {
        override def run(): Unit = f(value)
    }).start()

  }

}
