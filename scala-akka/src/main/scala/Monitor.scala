import akka.actor.{Props, Actor}
import akka.actor.Actor.Receive

class Monitor(val numberOfActors : Int, val numberOfMessages : Int) extends Actor {

  var initStart : Long = 0
  var initEnd : Long = 0
  var runEnd : Long = 0

  override def receive: Receive = {
    case (tag : String, time : Long) => tag match {
      case "initStart" => initStart = time
      case "initEnd" => initEnd = time
      case "runEnd" =>
        runEnd = time
        printResults()
        context.system.terminate()
    }
  }


  def printResults() {
    val initTime = initEnd - initStart
    val spawnTime = (initTime * 1000.0) / numberOfActors
    println("init_time = " + initTime + " ms (" + spawnTime + " us/proc) (" + numberOfActors + " procs)")

    val runTime = runEnd - initEnd
    val msgTime = (runTime * 1000.0) / numberOfMessages
    println("run_time = " + runTime + " ms (" + msgTime + " us/msg) (" + numberOfMessages + " msgs)")
  }
}

object Monitor {
  def props(numberOfActors : Int, numberOfMessages : Int) = Props(new Monitor(numberOfActors, numberOfMessages))
}
