package ramdoms

import types.StateTypes._

class RandomGeneratorState {

  def map[A,B](s:RandomState[A])(f:A=>B):RandomState[B] = (random) => {
    val (value, nextRandom) = s(random)
    (f(value),nextRandom)
  }

}
