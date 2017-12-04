package parsers.model

case class ParserModel[+A] (element:A) {

  def isEqualTo[B >: A](c:B):Boolean =
    element == c
}
