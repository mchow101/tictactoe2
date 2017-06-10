import java.util.Scanner;

public class TicTacToeGame {
	public static void main(String[]args) {
		//Setting up Board
		Scanner scan = new Scanner(System.in);
		TicTacToeBoard board = new TicTacToeBoard(630, 690);
		int[][] boardLines = {
			{10, 200, 600, 200},
			{10, 400, 600, 400},
			{200, 10, 200, 600},
			{400, 10, 400, 600}};
		board.defineBoard(boardLines);
		int[][] theBoard = new int[3][3];
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				theBoard[i][j] = 0;
			}
		}
		board.setBoard(theBoard);
		board.setFiles("number-1-icon.jpg", "number-2-icon.jpg");
		board.repaint();
		//Instructions and Menu
		System.out.println("Welcome to the Ultimate Tic Tac Toe!");
		System.out.println("To play, simply type the row and column in which you want to place your piece.");
		System.out.println("Try to get three in a row in any direction!");
		System.out.print("Do you want to play (1) single player or (2) multiplayer? ");
		int player = scan.nextInt();
		int turns = 0;
			if(player == 2) {
			//Two Player Game
			boolean gameOn = true;
			int turn = 2;
			while(gameOn == true) {
				turns++;
				if(turn == 1)
					turn = 2;
				else
					turn = 1;
				theBoard = game(turn, theBoard);
				board.setBoard(theBoard);
				board.repaint();
				if(checkGame(turn, theBoard) == true) {
					String s = "Player " + turn + " won! Good game.";
					board.setWinner(s, 200, 630, 20);
					board.showText(true);
					System.out.println(s);
					board.repaint();
					gameOn = false;
				}
				if(turns==9 && gameOn == true) {
					String s = "The game tied!";
					board.setWinner(s, 250, 630, 20);
					board.showText(true);
					System.out.println(s);
					board.repaint();
					gameOn = false;
				}
			}
		}

		else {
			//One Player Game
			boolean gameOn = true;
			int turn = 2;
			while(gameOn == true) {
				turns++;
				if(turn == 1) {
					theBoard = computer(theBoard);
					turn = 2;
				}
				else {
					turn = 1;
					theBoard = game(turn, theBoard);
				}
				board.setBoard(theBoard);
				board.repaint();
				if(checkGame(turn, theBoard) == true) {
					String s = "";
					if(turn == 1)
						s = "You won! Good game.";
					else
						s = "The computer won! Good game.";
					board.setWinner(s, 200, 630, 20);
					System.out.println(s);
					board.showText(true);
					board.repaint();
					gameOn = false;
				}
				if(turns==9 && gameOn == true) {
					String s = "The game tied!";
					board.setWinner(s, 250, 630, 20);
					board.showText(true);
					System.out.println(s);
					board.repaint();
					gameOn = false;
				}
			}
		}
	}

	public static int[][] game(int turn, int[][] arr) {
		boolean bleh = true;
		while(bleh == true) {
			Scanner scan = new Scanner(System.in);
			System.out.print("Choose a row: ");
			int row = scan.nextInt();
			System.out.print("Choose a column: ");
			int column = scan.nextInt();
			if(row > 3 || column > 3)
			    System.out.println("Not a spot. Try again.");
			else if(arr[row - 1][column - 1] == 0) {
				arr[row - 1][column - 1] = turn;
				bleh = false;
			}
			else
				System.out.println("This spot is taken. Try again.");
		}
		return arr;
	}

	public static boolean checkGame(int turn, int[][] arr) {
		boolean bleh = false;
		for(int i = 0; i < 3; i++) {
			if(arr[i][0]==arr[i][2] && arr[i][0]==arr[i][1] && arr[i][0]==turn) {
				bleh = true;
				break;
			}
			if(arr[0][i]==arr[2][i] && arr[0][i]==arr[1][i] && arr[0][i]==turn) {
				bleh = true;
				break;
			}
		}
		if(arr[0][0]==arr[2][2] && arr[0][0]==arr[1][1] && arr[0][0]==turn)
			bleh = true;
		else if(arr[0][2]==arr[2][0] && arr[0][2]==arr[1][1] && arr[2][0]==turn)
			bleh = true;
		return bleh;
	}

	public static int[][] computer(int[][] arr) {
		boolean bleh = true;
			while(bleh == true) {
				int row = (int) (Math.random()*3);
				int column = (int) (Math.random()*3);
				if(arr[row][column] == 0) {
					arr[row][column] = 2;
					bleh = false;
				}
			}
		return arr;
	}
}
