package Controller;

public class Main {
    public static void main(String[] args) {
        //Display.DisplayBigBoard(Display.InitializeBoards(),new int[]{-1,-1});
        Gameplay.GameplayLoop();
        System.out.println(Gameplay.checkForWinner(Gameplay.bigBoard.getBoard()[0][0].getBoard()));//OOP
    }
}
