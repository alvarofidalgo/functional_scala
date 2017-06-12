package parallelims.types

import parallelims.api.{ExecutionService, Future}


object Types {

  type Par[A] = (ExecutionService) => Future[A]

}
