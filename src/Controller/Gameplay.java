package Controller;

import View.BigBoard;
import View.Console;
import View.Display;

public class Gameplay {
    static BigBoard bigBoard;
    static boolean gameWon = false;
    //:)
    public static void GameplayLoop() {
        int turns = 0;
        String[] playerNames = {"player 1", "player 2"};
        char[] playerSymbols = {'X', 'O'};
        int[] coordinates = new int[]{-1, -1};

        //call different functions :)
        bigBoard = Display.InitializeBoards();

        do {
            coordinates = playerTurn(playerNames[turns % 2], playerSymbols[turns % 2], bigBoard, coordinates);

            if (checkForWinner(bigBoard.getBoard()[coordinates[0]][coordinates[1]].getBoard())) {
                fillBoard(bigBoard.getBoard()[coordinates[0]][coordinates[1]].getBoard(), playerSymbols[turns % 2]);
            }
            turns++;
        } while (!gameWon);
    }

    public static int[] playerTurn(String playerName, char playerSymbol, BigBoard board, int[] smallBoard) {
        System.out.println(playerName + ", it's your turn!"); //Tell the player it's their turn

        boolean validCoordinate = false;
        int row = smallBoard[0];
        int col = smallBoard[1];
        int smallRow;
        int smallCol;
        //Choose small board if coords are -1 -1
        if (smallBoard[0] == -1 && smallBoard[1] == -1) {

            do {
                Display.DisplayBigBoard(board, new int[]{row, col});
                row = Console.getInteger("Which row would you like to place in?\n", 1, 3) - 1; //Ask them for a row
                col = Console.getInteger("Which column would you like to place in?\n", 1, 3) - 1; //Ask them for a column
                if (!checkForFullBoard(board, new int[]{row, col})) { //Check if they can place there (not already placed)
                    validCoordinate = true;
                }
                else Console.println("NOT VALID SPOT! PICK AGAIN", Console.RED);
            } while (!validCoordinate);
        }
        validCoordinate = false;
        char[][] playingBoard = board.getBoard()[row][col].getBoard(); //Find the board the player needs to place on
        do {
            Display.DisplayBigBoard(board, new int[]{row, col});
            smallRow = Console.getInteger("Which row would you like to place in?\n", 1, 3) - 1; //Ask them for a row
            smallCol = Console.getInteger("Which column would you like to place in?\n", 1, 3) - 1; //Ask them for a column
            if (playingBoard[smallRow][smallCol] == ' ') { //Check if they can place there (not already placed)
                validCoordinate = true;
                playingBoard[smallRow][smallCol] = playerSymbol;
            }
        } while (!validCoordinate);
        if (checkForWinner(bigBoard.getBoard()[row][col].getBoard())) {
            fillBoard(bigBoard.getBoard()[row][col].getBoard(), playerSymbol);
        }

        return new int[]{smallRow, smallCol};//return the coordinates the player placed in
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

    public static void fillBoard(char[][] board, char playerSymbol) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = playerSymbol;
            }
        }
    }

    public static boolean checkForFullBoard(BigBoard board, int[] coordinates) {
        for (int smallRow = 0; smallRow < 3; smallRow++) {
            for (int smallCol = 0; smallCol < 3; smallCol++) {
                //Check if the smallboard is full
                if (board.getBoard()[coordinates[0]][coordinates[1]].getBoard()[smallRow][smallCol] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

}
