package View;

public class Board {

    char[][] board = {
        {' ',' ',' '},
        {' ',' ',' '},
        {' ',' ',' '}
    };

    char fillCharacter = ' ';


    public char[][] getBoard() {
        return board;
    }

    public void setBoard(char[][] board) {
        this.board = board;
    }

    public char getFillCharacter() {
        return fillCharacter;
    }

    public void setFillCharacter(char fillCharacter) {
        this.fillCharacter = fillCharacter;
    }
}
