import akka.actor.{PoisonPill, Actor, ActorRef, Props}

class Zog(val monitor : ActorRef) extends Actor {

  private var next : ActorRef = _

  override def receive = {
    case 0 =>
      monitor ! ("runEnd", System.currentTimeMillis())
    case m : Int =>
      next ! m-1
    case nextActor : ActorRef =>
      next = nextActor
  }

}

object Zog {
  def props(monitor : ActorRef) : Props = Props(new Zog(monitor))
}