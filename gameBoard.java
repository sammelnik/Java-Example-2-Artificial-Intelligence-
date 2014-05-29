


import aima.core.agent.Action;
import aima.core.agent.impl.DynamicAction;

import java.util.Random;

/**
 * @author sammelnik
 * 
 */
public class gameBoard {
	private char board[][];
	public int n;
	public static Action LEFT = new DynamicAction("LEFT");
	public static Action RIGHT = new DynamicAction("RIGHT");
	public static Action DOWN = new DynamicAction("DOWN");
	public static Action UP = new DynamicAction("UP");
	private int X;
	private int Y;
	public int door1X;
	public int door1Y;
	public int door2X;
	public int door2Y;

	public int endX;
	public int endY;
	public boolean oneforth;

	public gameBoard() {
		n = 8;
		board = new char[8][8];
		for (int i = 0; i < n - 1; i++) {
			for (int j = 0; j < n - 1; j++) {
				board[i][j] = ' ';
			}
		}
		generateBoard();

	}

	public gameBoard(int size) {
		if (size >= 1) {
			n = size;
			board = new char[size][size];
			for (int i = 0; i < n - 1; i++) {
				for (int j = 0; j < n - 1; j++) {
					board[i][j] = ' ';
				}
			}
			generateBoard();
		}
	}

	public gameBoard(gameBoard object) {
		this.board = object.board;
		this.n = object.n;
		this.setX(object.X);
		this.setY(object.Y);
		endX=object.endX;
		endY=object.endY;
		door1X = object.door1X;
		door1Y = object.door1Y;
		door2X = object.door2X;
		door2Y = object.door2Y;
	}

	public void generateBoard() {
		board[1][1] = 'I'; // place start position
		setX(1);
		setY(1);
//		Random generator = new Random();
//		
//		int randomPosX = generator.nextInt(n - 1); // get start position x
//		int randomPosY = generator.nextInt(n - 1); // get start position y
//		board[randomPosX][randomPosY] = 'I'; // place start position
//		setX(randomPosX);
//		setY(randomPosY);
//		int endPosX = generator.nextInt(n - 1); // get end position x
//		int endPosY = generator.nextInt(n - 1); // get end position y
//		while (randomPosX == endPosX && randomPosY == endPosY) { // check if the
//																	// start in
//																	// end
//																	// positon
//																	// is the
//																	// same
//			endPosX = generator.nextInt(n - 1);
//			endPosY = generator.nextInt(n - 1);
//		}
		board[6][6] = 'E'; // place end position on board
		endX = 6;
		endY = 6;
//		for (int i = 0; i < n - 1; i++) {
//			for (int j = 0; j < n - 1; j++) {
//				if (board[i][j] == ' ') {
//					int walls = generator.nextInt(100);
//					if(oneforth){
//					if (walls % 4 == 0) {
//						board[i][j] = 'X'; // Places the X in 1/4 of the board
//					}
//					}else{
//					if (walls % 8 == 0) { //Places the X in 3/8ths of the board
//						board[i][j] = 'X'; 
//					}
//					if (walls % 8 == 1) {
//						board[i][j] = 'X';
//					}
//					if (walls % 8 == 2) {
//						board[i][j] = 'X';
//					}
//					}
//				}
//			}
//		}
		int where = 3; // Generates the door 
		for(int i=0; i<n-1; i++){
			if(board[where][i]!='I'  && board[where][i]!='E') // checks if there is not an E or an I
				board[where][i]='X';
			if(board[where+1][i]!='I'  && board[where+1][i]!='E')// checks if there is not an E or an I
				board[where+1][i]='X';
		}
		if(board[where][0]!='I'  && board[where][0]!='E')
			board[where][0]='D';
		if(board[where+1][0]!='I'  && board[where+1][0]!='E')
			board[where+1][0]='X';
		if(board[where+1][n-2]!='I'  && board[where+1][n-2]!='E')
			board[where+1][n-2]='D';
		door2X=where+1;
		door2Y=n-2;
		door1X=where;
		door1Y=0;
	}

	public void printBoard() {
		System.out.println("==============");

		for (int i = 0; i < n - 1; i++) {
			for (int j = 0; j < n - 1; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}

		System.out.println("==============");
		System.out.println("I am at "+X +Y);
	}
	public void moveUp() {
		if (canMove(UP)) {
			setX(X - 1);
		}
		if(board[X][Y] == 'D'){
			board[X][Y]='U';

			findTeleport(board);
		}

		if(board[X][Y]!='E')
			board[X][Y]='I';
		
		if (board[X][Y] != 'E' || board[X][Y] != 'I')
			board[X][Y] = '.';
	}

	public void moveDown() {
		if (canMove(DOWN)) {
			setX(X + 1);

		}
		if(board[X][Y] == 'D'){
			board[X][Y]='U';
			findTeleport(board);
		}
		if(board[X][Y]!='E')
			board[X][Y]='I';

		if (board[X][Y] != 'E' || board[X][Y] != 'I')
			board[X][Y] = '.';

	}

	public void moveLeft() {
		if (canMove(LEFT)) {
			setY(Y - 1);
		}
		if(board[X][Y] == 'D'){
			board[X][Y]='U';

			findTeleport(board);
		}

		if(board[X][Y]!='E')
			board[X][Y]='I';

		if (board[X][Y] != 'E' || board[X][Y] != 'I')
			board[X][Y] = '.';

	}

	public void moveRight() {
		if (canMove(RIGHT)) {
			setY(Y + 1);
		}
		if(board[X][Y] == 'D'){
			board[X][Y]='U';

			findTeleport(board);
		}

		if(board[X][Y]!='E')
			board[X][Y]='I';

//		if (board[X][Y] != 'E' || board[X][Y] != 'I')
//			board[X][Y] = '.';

	}
	private void findTeleport(char[][] board2) {
		for(int i=0; i<7; i++){
			for(int j=0; j<7; j++){
				if(board2[i][j]=='D'){
					if(board[i-1][j]=='X'){
						X=i;
						Y=j-1;
					}else{
						X=i;
						Y=j+1;
					}
				}
			}
		}
	}
	public boolean canMove(Action a) {
		if ((Y - 1) < 0) {
			return false;
		}
		if ((X - 1) < 0) {
			return false;
		}
		
		printBoard();
		if (a.equals(UP)) {
			if (board[X-1][Y] == ' ' || board[X-1][Y] == 'E') {
				return true;
			}
		}
		if (a.equals(DOWN)) {
			if (board[X+1][Y] == ' ' || board[X+1][Y] == 'E') {
				return true;
			}
		}
		if (a.equals(LEFT)) {
			if (board[X][Y-1] == ' ' || board[X][Y-1] == 'E') {
				return true;
			}
		}
		if (a.equals(RIGHT)) {
			if (board[X][Y+1] == ' ' || board[X][Y+1] == 'E') {
				return true;
			}
		}
		if (a.equals(UP)) {
			if (board[X-1][Y] == 'D') {
				return true;
			}
		}
		if (a.equals(DOWN)) {
			if (board[X+1][Y] == 'D') {
				return true;
			}
		}
		if (a.equals(LEFT)) {
			if (board[X][Y-1] == 'D') {
				return true;
			}
		}
		if (a.equals(RIGHT)) {
			if (board[X][Y+1] == 'D' ) {
				return true;
			}
		}
		if (a.equals(UP)) {
			if (board[X-1][Y] == 'X') {
				return false;
			}
		}
		if (a.equals(DOWN)) {
			if (board[X+1][Y] == 'X') {
				return false;
			}
		}
		if (a.equals(LEFT)) {
			if (board[X][Y-1] == 'X') {
				return false;
			}
		}
		if (a.equals(RIGHT)) {
			if (board[X][Y+1] == 'X' ) {
				return false;
			}
		}

		
		return false;
	}

	public boolean atGoal() {
		if (board[X][Y] == 'E')
			return true;
		return false;
	}

	public int getX() {
		return X;
	}

	public void setX(int x) {
		X = x;
	}

	public int getY() {
		return Y;
	}

	public void setY(int y) {
		Y = y;
	}

	public int currentPositionX() {
		for (int i = 0; i < n - 1; i++) {
			for (int j = 0; j < n - 1; j++) {
				if (board[i][j] == 'I') {
					return i;
				}
			}
		}
		return 0;
	}

	public int currentPositionY() {
		for (int i = 0; i < n - 1; i++) {
			for (int j = 0; j < n - 1; j++) {
				if (board[i][j] == 'I') {
					return j;
				}
			}
		}
		return 0;
	}

}
