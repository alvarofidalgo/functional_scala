package parallelims.api
import scala.concurrent.duration.TimeUnit



class ExecutionService {


    def submit[A](callable: Callable[A]):Future[A] = new Future[A] {
      override def get: A = callable.call

      override def get(timeOut: Long, unit: TimeUnit): A = ???
    }

}
