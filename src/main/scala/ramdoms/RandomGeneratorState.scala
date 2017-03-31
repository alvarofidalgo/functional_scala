package ramdoms

import types.StateTypes._

object RandomGeneratorState {

    implicit class RandomGeneratorState[A](s: RandomState[A]){

      def map[B](f: A => B): RandomState[B] = (random) => {
        val (value, nextRandom) = s(random)
        (f(value), nextRandom)
      }

      def map2[B,C](random:RandomState[B])(f:(A,B)=>C) : RandomState[C] = (r)=> {
        val (value,n) = s(r)
        val (value2,n2) = random(r)
        (f(value,value2),n)
      }

      def toDoubleRand(random:RandomGenerator):RandomState[Double] =
        s.map((a) => {
          val min = random.limits._1
          val max = random.limits._2
          val road = max - min
          ((random.nextInt._1 - min) % max).toDouble / road.toDouble
        })

      def flatMap[B](f:A=>RandomState[B]):RandomState[B] = ???
    }
}
