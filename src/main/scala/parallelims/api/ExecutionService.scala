package parallelims.api


class ExecutionService {


    def submit[A](callable: Callable[A]):Future[A] = new Future[A] {
      override def get: A = callable.call
    }

}
