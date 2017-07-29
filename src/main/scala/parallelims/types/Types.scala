package parallelims.types

import parallelims.api.Future
import parallelims.impl.ExecutionService


object Types {

  type Par[A] = (ExecutionService) => Future[A]

}
