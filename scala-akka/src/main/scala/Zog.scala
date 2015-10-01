import akka.actor.{PoisonPill, Actor, ActorRef, Props}

class Zog() extends Actor {

  private var next : ActorRef = _

  override def receive = {
    case 0 =>
      context.system.terminate()
      println("terminate")
    case m : Int =>
      next ! m-1
      println(self.path + " : " + m)
    case nextActor : ActorRef =>
      next = nextActor
      println(next.path)
  }

}

object Zog {
  def props : Props = Props(new Zog)
}