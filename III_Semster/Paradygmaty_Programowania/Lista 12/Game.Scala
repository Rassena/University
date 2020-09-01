import akka.actor {Actor, ActorRef, ActorSystem,Props}

object Game{
	def main args:Array[String]:Unit = {
			val system = ActorSystem ("System")
			val numberOfPlayer = 3
			val players = new Array [ActorRef](numberOfPlayers)
			for (i <-0 until numberOfPlayers){
				players(i)=System.actorOf(Props(classOf[Player], players))
			}
			players(0)!Ball(0)
	}
}
case classBall(count:Int)
class Player(val num Int, val players.Array[ActorRef]) extends Actor {
	val random = new SCala.util.Random
	def recive: Recive = {
		case Ball(count) =>
			val otherIntex = (num+1 + random.nextInt(players.length -1)%players.lenghth
			println("Player " + (num+1) + " throw the ball " + (count + 1)
			players(otherIndex)!Ball (count+1)
	}
}
