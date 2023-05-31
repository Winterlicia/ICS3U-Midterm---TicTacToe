import arc.*;

public class MidtermDraft {
	
	//We use "static" so that it belongs to the overall class and not a specific instance
	//This will allow us to use these variables between many different instances.
	static String[] board;
	static String strTurn;
	static String strPlayer1;
    static String strPlayer2;
    static int intNumPlayer1Wins = 0;
    static int intNumPlayer2Wins = 0;
	
	public static void main(String[]args) {
		Console con = new Console();
	
		//Initializing variables
		int intArray;
		board = new String[9]; //used to create an array within the "main" method
        String strName; 
        int intPlayerInput;
        int intTurn;
        boolean blnHasWinner; 
        int intPlayerTurn;
        String strResponse;
        
        //Start of the game where we ask for the player's names
        con.println("Welcome to 2-player tic-tac-toe!");
		con.println("Player 1, enter your name: ");
		strPlayer1 = con.readLine();
		con.println(strPlayer1+" ,you will use the character 'O'.");
		con.println();
		con.println("Player 2, enter your name: ");
		strPlayer2 = con.readLine();
		con.println(strPlayer2+" ,you will use the character 'X'.");
        con.println();
        
        //Using "while true" is just a way of running a loop until we can explcitily break out of it using 'break;' or 'return;'
		while (true) {
			blnHasWinner = false; 
			intTurn = 0;
			//Generating the array to be used in the main method.
			for (intArray = 0; intArray < 9; intArray++) {
				board[intArray] = String.valueOf(intArray+1); 
			}
			//print the original playing board to the console
			printBoard(con);
			con.println();
			
			//while loop created to ensure that there is no winner, and the intTurn is less than 0 
			//This will allow the game to keep looping until the game ends
			while (!blnHasWinner && intTurn < 9) {
				
				intPlayerTurn = intTurn % 2; //Modulus will alternate each time intTurn increases
				
				//? : syntax -- explained in the VICO table
				/*This will allow us to have the same organized inputs for player 1 and player 2,
				  rather than having two if-else statements, allowing us to shorten the code.*/
				strName = intPlayerTurn == 0 ? strPlayer1 : strPlayer2;
							
				//The previous statement determines who's turn it is
				//This statement is asking for the input
				con.println(strName + ", choose any position from 1 to 9: ");
				intPlayerInput = con.readInt();
				
				//This while loop is to check validity of the input
				//Ensure intPlayerInput is in the range of 1 to 9.
				while (true) {
					if (intPlayerInput > 9 || intPlayerInput < 1) {
						con.println("This position does not exist! Choose another position: ");
						intPlayerInput = con.readInt();
					} else {
						break; //break out of loop if position is valid
					}
				}
				// Once validity is checked, while loop is used to check board occupancy
				while (!board[intPlayerInput - 1].equals(String.valueOf(intPlayerInput))) {
					con.println("This position has already been taken! Choose another position: ");
					intPlayerInput = con.readInt();
				}
				//Calculation to assign the value to board. Then the board is printed.
				//How it works is explained in VICO table.
				board[intPlayerInput - 1] = intPlayerTurn == 0 ? "O" : "X";
				printBoard(con);
				con.println();
				
				//checking winner by assigning the boolean value to hasWinner
				//explained in VICO table
				blnHasWinner = checkWinner(con);
				intTurn++; //turn increments after every turn.
			}
			//By the ninth turn, if there is still no winner, declare a draw game.
			if(!blnHasWinner) {
				con.println("Draw game!");
			}
			con.println("The game has ended.");
			
			//printing out the counted number of wins
			con.println(strPlayer1+"'s number of wins is: "+intNumPlayer1Wins);
			con.println(strPlayer2+"'s number of wins is: "+intNumPlayer2Wins);
			
			con.println("Play again? yes/no");
			strResponse = con.readLine();
			if (strResponse.equalsIgnoreCase("no")) {
				con.println("Play again next time!");
				break; //break out of the "while true" loop.
			}
		}
	}
		
	// function to print out the board:
    public static void printBoard(Console con) {
	  //the board will overtime have new values as inputs are always being inputted
	  //therefore we use a function to print out the board everytime, called printBoard()
        con.println(" "+board[0] + " | " + board[1] + " | " + board[2]);
        con.println("---+---+---");
        con.println(" "+board[3] + " | " + board[4] + " | " + board[5]);
        con.println("---+---+---");
        con.println(" "+board[6] + " | " + board[7] + " | " + board[8]);
    }
    
    // function to check the winner using boolean
    public static boolean checkWinner(Console con) {
		String strWinner = "";
		
		//In 3x3 tic-tac-toe, there are 8 total possible ways to win.
		
		//make a bunch of if statements to check for a win:
		//Case 1: horizontal win via 0, 1, 2:
		if (board[0].equals(board[1]) && board[1].equals(board[2])) {
			strWinner = board[0] == "O" ? strPlayer1 : strPlayer2;
		} 
		
		//Case 2: horizontal win via 4, 5, 6
		if (board[3].equals(board[4]) && board[4].equals(board[5])) {
			strWinner = board[3] == "O" ? strPlayer1 : strPlayer2;
		}
		//Case 3: horizontal win via 7, 8, 9
		if (board[6].equals(board[7]) && board[7].equals(board[8])) {
			strWinner = board[6] == "O" ? strPlayer1 : strPlayer2;
		}
		//Case 4: Vertical win via 1, 4, 7
		if (board[0].equals(board[3]) && board[3].equals(board[6])) {
			strWinner = board[0] == "O" ? strPlayer1 : strPlayer2;
		}
		//Case 5: Vertical win via 2, 5, 8
		if (board[1].equals(board[4]) && board[4].equals(board[7])) {
			strWinner = board[1] == "O" ? strPlayer1 : strPlayer2;
		}
		//Case 6: Vertical win via 3, 6, 9
		if (board[2].equals(board[5]) && board[5].equals(board[8])) {
			strWinner = board[2] == "O" ? strPlayer1 : strPlayer2;
		}
		//Case 7: Diagonal win via 1, 5, 9
		if (board[0].equals(board[4]) && board[4].equals(board[8])) {
			strWinner = board[0] == "O" ? strPlayer1 : strPlayer2;
		}
		//Case 8: Diagonal win via 3, 5, 7
		if (board[2].equals(board[4]) && board[4].equals(board[6])) {
			strWinner = board[2] == "O" ? strPlayer1 : strPlayer2;
		}
		
		/*when the winner is not empty (java function), we can return the number of wins for both players.
		  If statements are used to ensure that the "wins" variable only increments, when either the 1st or 2nd player is declared the winner.
		  */
		if (!strWinner.isEmpty()) {
			con.println(strWinner + " has won the game!");
			if (strWinner.equals(strPlayer1)) { 
				intNumPlayer1Wins++; //increment the number of wins by 1 when strPlayer1 wins
			} else if (strWinner.equals(strPlayer2)) {
				intNumPlayer2Wins++; //similarly increment when player 2 wins
			}
			return true; 
			//return true and increment when the winner is not empty 
		} else {
			return false; 
			//if it returns false, the 'wins' variables will not increment as it would be a draw
		}		
	}
}
