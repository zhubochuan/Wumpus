package Game;

public class Gold extends GameItem{
	Gold(char c){
		super(c);
	}
	public void sense() {//gold reminder
		System.out.println("a faint glitter nearby!");
	}
}
