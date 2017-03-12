package ramdoms

import types.StateTypes._

object RandomGeneratorState {

    implicit class RandomGeneratorState[A](s: RandomState[A]){

      def map[B](f: A => B): RandomState[B] = (random) => {
        val (value, nextRandom) = s(random)
        (f(value), nextRandom)
      }

      def toDoubleRand(random:RandomGenerator):RandomState[Double] =
        s.map((a) => {
          val min = random.limits._1
          val max = random.limits._2
          val road = max - min
          ((random.nextInt._1 - min) % max).toDouble / road.toDouble
        })


    }
}
