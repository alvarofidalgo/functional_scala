package parallelism.impl


class ExecutionNonBlocking[A](value:A,time:Int) {

  def submit(f:A=>Unit):Unit = {
    new Thread(new Runnable {

        override def run(): Unit = {

          Thread.sleep(time * 1000)
          f(value)
        }
    }).start()

  }

}
