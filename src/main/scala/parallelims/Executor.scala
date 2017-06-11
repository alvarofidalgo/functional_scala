package parallelims


class Executor {


  def unit[A](unit: =>A) : Par[A] = new ParImplement[A](unit)

}
