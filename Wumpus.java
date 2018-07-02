package Game;

public class Wumpus extends GameItem {

	Wumpus(char c) {
		super(c);
	}
	public void sense(){ //reminder to the player
		System.out.println("a vile smell nearby!");
	}
}
