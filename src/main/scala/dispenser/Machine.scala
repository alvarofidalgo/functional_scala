package dispenser

trait Machine[P[_]] {


  def select():P[String]
}
