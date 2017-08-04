package parallelism.types

import parallelism.api.Future
import parallelism.impl.ExecutionService


object Types {

  type Par[A] = (ExecutionService) => Future[A]

}
