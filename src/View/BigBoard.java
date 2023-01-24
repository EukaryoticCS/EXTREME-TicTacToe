package View;

public class BigBoard {


    Board[][] board = new Board[3][3];

    boolean isCompleted;

    public Board[][] getBoard() {
        return board;
    }

    public void setBoard(Board[][] board) {
        this.board = board;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}
