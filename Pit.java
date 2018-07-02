package Game;

public class Pit extends GameItem{
	Pit(char c){
		super(c);
	}
	public void sense() {//reminder to the player
		System.out.println("a breeze nearby!");
	}
}
