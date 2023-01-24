package View;

public class Display {


    public static void DisplayTheBoard()
    {


    }

    public static void DisplayBigBoard()
    {



    }

    public static void InitializeBoards()
    {
        BigBoard bigBoard = new BigBoard();
        Board smallBoard = new Board();

        for(int rows = 0; rows < bigBoard.board.length; rows++ ){
            System.out.println("| ");
            for(int smallRows = 0; smallRows< smallBoard.board.length;smallRows++)
            {
                Console.print("| ", Console.BLUE);
                for (int column = 0; column < bigBoard.board[1].length; column++){
                    System.out.println(" | ");
                    for (int smallColumn = 0; smallColumn < smallBoard.board[1].length; column++){
                        Console.print(" | ",Console.BLUE);
                    }
                }

            }


            System.out.println();
        }

    }
}
