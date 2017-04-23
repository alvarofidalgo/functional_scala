package dispenser


sealed trait TypeState

case object SELECTED extends TypeState
case object PAYMENT extends TypeState
