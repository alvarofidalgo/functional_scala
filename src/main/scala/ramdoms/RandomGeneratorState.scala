package ramdoms

import types.StateTypes._

object RandomGeneratorState {

    implicit class RandomGeneratorState[A](s: RandomState[A]){

      def map[B](f: A => B): RandomState[B] = (random) => {
        val (value, nextRandom) = s(random)
        (f(value), nextRandom)
      }

      def toDoubleRand:RandomState[Double] = (rng) => (0,rng)


    }
}
