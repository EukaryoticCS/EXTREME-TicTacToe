package Controller;

import View.BigBoard;
import View.Display;

public class Gameplay {
static  BigBoard bigBoard;
static boolean gameWon = false;
    public static void GameplayLoop() {
        int playerTurn = 0;
        String[] playerNames = {"player 1", "player 2"};
        char[] playerSymbols = {'X', 'O'};


        //call different functions :)
        bigBoard = Display.InitializeBoards();

        do {

            if(playerTurn%2 ==0){
                System.out.println(playerNames);

            }


        }while(!gameWon);
    }

    public static boolean checkForWinner(char[][] board) {
        for (int x = 0; x < board.length; x++) //Loops through 0-2 to get each row/col
        {
            if (board[x][0] != ' ' && board[x][0] == board[x][1] && board[x][0] == board[x][2]) //Checks the column
                return true;
            else if (board[0][x] != ' ' && board[0][x] == board[1][x] && board[0][x] == board[2][x]) //Checks the row
                return true;
        }
        if (board[0][0] != ' ' && board[0][0] == board[1][1] && board[0][0] == board[2][2]) //Diagonal down-right
            return true;
        if (board[2][0] != ' ' && board[2][0] == board[1][1] && board[2][0] == board[0][2]) //Diagonal down-left
            return true;
        return false;
    }


}
