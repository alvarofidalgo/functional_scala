package matchers

import java.util.concurrent.TimeUnit

import org.scalatest.matchers.{BeMatcher, MatchResult, Matcher}
import parallelism.api.Future
import parallelism.impl.ExecutionService
import parallelism.types.Types.Par
import streams.Empty
import streams.InitStreams
import streams.Streams


object CustomMatchers {

 implicit class equalToStream[A](s: Streams[A]) extends BeMatcher[Streams[A]] {

    override def apply(left: Streams[A]): MatchResult = {

      def loop(s:Streams[A],a:Streams[A])(implicit result:Boolean = true) : Boolean = (s,a) match {
        case (InitStreams(head1, tail1),InitStreams(head2, tail2)) => loop(tail1(),tail2())(result && head1()==head2())
        case (InitStreams(head1, tail1),Empty)  => false
        case (Empty,InitStreams(head2, tail2))  => false
        case (Empty,Empty)=> result

      }
      MatchResult(
        loop(s,left),
        s" Stream $left is not equals to $s",
        " Streams are equals"
      )
    }


  }

  implicit class parallelismIsEqualTo[A](par:Par[A]) extends BeMatcher[Par[A]] {


    override def apply(leftPar: Par[A]): MatchResult = {
      val executionService = new ExecutionService()

      val future = par(executionService)
      val left=leftPar(executionService)
      val withTime = future.get(timeOut = 1,
                                unit=  TimeUnit.NANOSECONDS).equals(left.get(timeOut = 1, unit=  TimeUnit.NANOSECONDS))
      val withoutTime = future.get.equals(left.get)
      MatchResult(
        withTime && withoutTime,
        s" Par $leftPar is not equals to $par",
        " Par are equals"
      )
    }

  }

  implicit class futureIsEqualTo[A](future:Future[A]) extends BeMatcher[Future[A]] {
    override def apply(left: Future[A]): MatchResult = {
      val withTime = future.get(timeOut = 1,
        unit=  TimeUnit.NANOSECONDS).equals(left.get(timeOut = 1, unit=  TimeUnit.NANOSECONDS))
      val withoutTime = future.get.equals(left.get)
      MatchResult(
        withTime && withoutTime,
        s" Future $left is not equals to $future",
        " Futures are equals"
      )
    }
  }


  implicit class eitherEqualTo[A](either:Either[Exception,A]) extends BeMatcher[Either[Exception,A]] {


    override def apply(left: Either[Exception,A]): MatchResult = {


      MatchResult(
        (left,either) match {

          case (Right(a),Right(b))=>a==b
          case (Left(a),Left(b))=>true
          case (_,_)=>false

        },
        s" Either $left is not equals to $either",
        " Eithers are equals"
      )

    }


  }




}
