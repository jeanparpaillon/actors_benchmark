import akka.actor.ActorSystem

/**
 * Created by gbecan on 10/1/15.
 */
object ZogApp extends App {

  val n = args(0).toInt
  val m = args(1).toInt

  // Creating actors
  val actorSystem = ActorSystem("zog")

  val first = actorSystem.actorOf(Zog.props, "0")

  var previous = first

  val actors = for (i <- 1 to n) yield {
    val actor = actorSystem.actorOf(Zog.props, i.toString)
    previous ! actor
    previous = actor
  }

  previous ! first

  first ! m

}
