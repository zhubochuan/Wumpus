package Game;

import java.util.Random;
import java.util.Scanner;

public class Game {
	//create a 2D array of 5*5 size
	
	private GameItem[][]board; //board
	
	private int row;	//player position
	private int col;	//player position
	
	private Scanner scan;
	
	Game(){
		board=new GameItem[5][]; //initialization  
		for(int i=0;i<5;i++) {	//default as '.' on the board
			board[i]=new GameItem[5];
			for(int j=0;j<5;j++) {
				board[i][j]=new ClearGround('.');
			}
		}
		this.setBoard();
		scan=new Scanner(System.in);
	}
	private void setBoard() {//create player position on the board
		Random randomGenerator=new Random();
		//random position in the beginning
		row=randomGenerator.nextInt(5);
		col=randomGenerator.nextInt(5);
		//initialize one wumpus position
		int wumpusx=randomGenerator.nextInt(5);
		int wumpusy=randomGenerator.nextInt(5);
		while(true) {
			if(!(wumpusx==row && wumpusy==col)) {
				board[wumpusx][wumpusy]=new Wumpus('W');
				break;
			}
		}
		//initialize golds
		int goldNum=randomGenerator.nextInt(4); //gold number from 0-3
		for(int i=0;i<goldNum;i++) {
			while(true) {
				int goldx=randomGenerator.nextInt(5); //position
				int goldy=randomGenerator.nextInt(5);
				if(board[goldx][goldy].getItemCharacter()=='.'&&!(goldx==row&&goldy==col)) {
					board[goldx][goldy] = new Gold('g');
					break;
				}
			}
		}
		//initialize pits, number is 3
		for(int i=0;i<3;i++) {
			while (true) {
				int pitX = randomGenerator.nextInt(5);
				int pitY = randomGenerator.nextInt(5);
				if (board[pitX][pitY].getItemCharacter() == '.'&&!(pitX == row && pitY == col)) {
					board[pitX][pitY] = new Pit('p');
					break;
				}
			}
		}
	}
	//display the board
	private void display() {
		System.out.println("board display");
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if(i==row&&j==col){ //player is '*'
					System.out.print("* ");
				}else{
					board[i][j].display();
				}
			}
			System.out.println();
		}
	}
	//displays text about what the player can sense from the elements
	//immediately surrounding the player
	private void senseNearby() {
		if (row > 0) {
			board[row - 1][col].sense();
		}
		if (row < 4) {
			board[row + 1][col].sense();
		}
		if (col > 0) {
			board[row][col - 1].sense();
		}
		if (col < 4) {
			board[row][col + 1].sense();
		}
	}
	/**
	 * provide a menu asking the user to make a choice from the following and
	 * obtain the user input:
	 */
	private String menu() {
		System.out.println("=====Wumpus====");
		System.out.println("1. Move player left");
		System.out.println("2. Move player right");
		System.out.println("3. Move player up");
		System.out.println("4. Move player down");
		System.out.println("5. Quit");

		String input = scan.next();
		return input;
	}
	//run
	public void runGame() {
		int score = 0;
		while (true) {
			this.display();
			this.senseNearby();
			String input = menu();
			if (input.equals("1")) { //when move at boundary, 
				col = (col + 4) % 5; //player will back at first position at this row or column.
			} else if (input.equals("2")) {
				col = (col + 1) % 5;
			} else if (input.equals("3")) {
				row = (row + 4 ) % 5;
			} else if (input.equals("4")) {
				row = (row + 1) % 5;
			} else if (input.equals("5")) {
				return;
			} else {
				System.out.println("wrong command");
			}
			char c = board[row][col].getItemCharacter();
			if (c == 'p' || c == 'W') {
				System.out.println("the player dies and the game ends,score is " + score);
				scan.close();
				return;
			} else if (c == 'g') {
				board[row][col] = new ClearGround('.');
				System.out.println("the game score increases by one");
				score++; //plus score
			}
		}
	}

}

