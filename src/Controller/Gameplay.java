package Controller;

import View.BigBoard;
import View.Console;
import View.Display;

public class Gameplay {
    static BigBoard bigBoard;
    static boolean gameWon = false;
    static boolean gameGarfie = false;

    //:)
    public static void GameplayLoop() {
        int turns = 0;
        String[] playerNames = {"player 1", "player 2"};
        char[] playerSymbols = {'X', 'O'};
        int[] coordinates = new int[]{-1, -1};

        //call different functions :)
        bigBoard = Display.InitializeBoards();

        do {
//            System.out.println("turn: " + turns); //For testing
            coordinates = playerTurn(playerNames[turns % 2], playerSymbols[turns % 2], bigBoard, coordinates);
            if (checkForBigBoardWin(bigBoard)) {
                gameWon = true;
            } else if (checkForBigBoardGarfield(bigBoard)) {
                gameGarfie = true;
            } else {
                turns++;
            }
        } while (!gameWon && !gameGarfie);
        if (gameWon) {
            Display.DisplayBigBoard(bigBoard, new int[]{-1, -1});
            Console.println(playerNames[turns % 2] + " wins!", Console.CYAN);
        } else {
            Display.DisplayBigBoard(bigBoard, new int[]{-1, -1});
            Console.println("Jesus christ how did you do that? It's a cats game", Console.CYAN);
            Console.println("Game winner is Garfield", Console.CYAN);
        }
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
                int tempRow = Console.getInteger("Which row would you like to place in?\n", 1, 3) - 1; //Ask them for a row
                int tempCol = Console.getInteger("Which column would you like to place in?\n", 1, 3) - 1; //Ask them for a column
                if (!checkForFullBoard(board, new int[]{tempRow, tempCol})) { //Check if they can place there (not already placed)
                    row = tempRow;
                    col = tempCol;
                    validCoordinate = true;
                } else Console.println("NOT VALID SPOT! PICK AGAIN", Console.RED);
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
            board.getBoard()[row][col].setFillCharacter(playerSymbol);
        }
        if (checkForGarfield(board, new int[]{row, col})) {
            fillBoard(bigBoard.getBoard()[row][col].getBoard(), 'C');
            board.getBoard()[row][col].setFillCharacter('C');
        }
        if (checkForFullBoard(board, new int[]{smallRow, smallCol})) {
            smallRow = -1;
            smallCol = -1;
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

    public static boolean checkForGarfield(BigBoard board, int[] coordinates) {
        if (checkForFullBoard(board, new int[]{coordinates[0], coordinates[1]}) && !checkForWinner(bigBoard.getBoard()[coordinates[0]][coordinates[1]].getBoard())) {
            //return lasagna
            return true;
        }
        //return mondays
        return false;
    }

    public static boolean checkForBigBoardWin(BigBoard board) {
        for (int x = 0; x < board.getBoard().length; x++) //Loops through 0-2 to get each row/col
        {
            if (checkForFullBoard(board, new int[]{x, 0}) && checkForFullBoard(board, new int[]{x, 1}) && checkForFullBoard(board, new int[]{x, 2})) {
                if (board.getBoard()[x][0].getFillCharacter() != 'C' && board.getBoard()[x][0].getFillCharacter() == board.getBoard()[x][1].getFillCharacter() && board.getBoard()[x][0].getFillCharacter() == board.getBoard()[x][2].getFillCharacter()) //Checks the column
                    return true;
            } else if (checkForFullBoard(board, new int[]{0, x}) && checkForFullBoard(board, new int[]{1, x}) && checkForFullBoard(board, new int[]{2, x})) {
                if (board.getBoard()[0][x].getFillCharacter() != 'C' && board.getBoard()[0][x].getFillCharacter() == board.getBoard()[1][x].getFillCharacter() && board.getBoard()[0][x].getFillCharacter() == board.getBoard()[2][x].getFillCharacter()) //Checks the row
                    return true;
            }
        }

        if (checkForFullBoard(board, new int[]{0, 0}) && checkForFullBoard(board, new int[]{1, 1}) && checkForFullBoard(board, new int[]{2, 2})) {
            if (board.getBoard()[0][0].getFillCharacter() != 'C' && board.getBoard()[0][0].getFillCharacter() == board.getBoard()[1][1].getFillCharacter() && board.getBoard()[0][0].getFillCharacter() == board.getBoard()[2][2].getFillCharacter()) //Checks the l-r diagonal
                return true;
        }
        if (checkForFullBoard(board, new int[]{2, 0}) && checkForFullBoard(board, new int[]{1, 1}) && checkForFullBoard(board, new int[]{0, 2})) {
            if (board.getBoard()[0][2].getFillCharacter() != 'C' && board.getBoard()[0][2].getFillCharacter() == board.getBoard()[1][1].getFillCharacter() && board.getBoard()[0][2].getFillCharacter() == board.getBoard()[2][0].getFillCharacter()) //Checks the r-l diagonal
                return true;
        }
        return false;
    }

    public static boolean checkForBigBoardGarfield(BigBoard board) {
        int filledBoards = 0;
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (checkForFullBoard(board, new int[] {row, col}))
                    filledBoards++;
            }
        }
        return filledBoards == 9;
    }


}
