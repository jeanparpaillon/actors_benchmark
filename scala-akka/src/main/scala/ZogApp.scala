import akka.actor.ActorSystem

object ZogApp extends App {

  val numberOfActors = args(0).toInt
  val numberOfMessages = args(1).toInt

  // Starting actor system
  val initStart = System.currentTimeMillis()
  val actorSystem = ActorSystem("zog")

  // Creating monitor actor
  val monitor = actorSystem.actorOf(Monitor.props(numberOfActors, numberOfMessages), "monitor")
  monitor ! ("initStart", initStart)

  // Creating actors for benchmark
  val first = actorSystem.actorOf(Zog.props(monitor), "0")

  var previous = first

  for (i <- 1 until numberOfActors) {
    val actor = actorSystem.actorOf(Zog.props(monitor), i.toString)
    previous ! actor
    previous = actor
  }

  previous ! first

  val initEnd = System.currentTimeMillis()
  monitor ! ("initEnd", initEnd)

  // Send messages
  first ! numberOfMessages

}
