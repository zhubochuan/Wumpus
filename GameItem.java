package Game;

public class GameItem {
	private char itemCharacter;
	
	public char getItemCharacter() {//itemCharacter getter
		return itemCharacter;
	}
	public void setItemCharacter(char itemCharacter) {//setter
		this.itemCharacter=itemCharacter;
	}
	
	public void display() { //display character
		System.out.print(itemCharacter+" ");
	}
	
	public void sense() { //feel item in the neighborhood
		//will override
	}
	GameItem(char c) { 
		itemCharacter=c;
	}
}
