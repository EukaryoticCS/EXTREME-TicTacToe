package View;

public class Display {


    public static void DisplayTheBoard() {


    }

    public static void DisplayBigBoard(BigBoard bigBoard, int[] coordinates) { //Takes in a board object and coordinates and based off the coordinates it will make that board area red
        for (int rows = 0; rows < 3; rows++) {
            for (int smallRows = 0; smallRows < 3; smallRows++) {
                for (int column = 0; column < 3; column++) {
                    for (int smallColumn = 0; smallColumn < 3; smallColumn++) {
                        System.out.print(bigBoard.board[rows][column].board[smallRows][smallColumn]);
                        if (smallColumn != 2) {
                            if ((coordinates[0] == rows || coordinates[0] == -1) && (coordinates[1] == column|| coordinates[1] == -1) )
                                Console.print(" | ", Console.RED);
                            else
                                Console.print(" | ", Console.BLUE);
                        }
                    }
                    if (column != 2)
                        Console.print(" || ", Console.CYAN);
                }
                System.out.println();
            }
            if (rows != 2)
                Console.println("==========||===========||===========", Console.CYAN);
        }
    }

    public static BigBoard InitializeBoards() {
        BigBoard bigBoard = new BigBoard();
        for (int row = 0; row < 3; row++) {

            for (int column = 0; column < 3; column++) {
                bigBoard.board[row][column] = new Board();
            }
        }
        return bigBoard;
    }
}
