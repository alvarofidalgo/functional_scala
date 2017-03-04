package matchers

import org.scalatest.matchers.BeMatcher
import org.scalatest.matchers.MatchResult
import streams.Empty
import streams.InitStreams
import streams.Streams

object StreamMatcher {

 implicit class equalToStream[A](s: Streams[A]) extends BeMatcher[Streams[A]] {

    override def apply(left: Streams[A]): MatchResult = {

      def loop(s:Streams[A],a:Streams[A])(implicit result:Boolean = true) : Boolean = (s,a) match {
        case (InitStreams(head1, tail1),InitStreams(head2, tail2)) => loop(tail1(),tail2())(result && head1()==head2())
        case (InitStreams(head1, tail1),Empty)  => false
        case (Empty,InitStreams(head2, tail2))  => false
        case (Empty,Empty)=>result
      }
      MatchResult(
        loop(s,left),
        left.toString + " NO ES CORRECTO",
        left.toString + "  ES CORECTO"
      )
    }


  }




}
