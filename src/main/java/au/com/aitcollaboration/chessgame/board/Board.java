package au.com.aitcollaboration.chessgame.board;

import au.com.aitcollaboration.chessgame.pieces.King;
import au.com.aitcollaboration.chessgame.pieces.Pieces;
import au.com.aitcollaboration.chessgame.pieces.Queen;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private Square[][] grid;
    private List<Pieces> piecesList;

    public static int BOARD_SIZE = 8;

    public Board() {
        this.piecesList = new ArrayList<Pieces>(2);
        this.grid = new Square[BOARD_SIZE][BOARD_SIZE];
        createBoard();
    }

    private void createBoard() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                grid[row][col] = new Square(row, col);
            }
        }
    }

    public void positionPieces() {
        Pieces white = piecesList.get(0);
        grid[0][0].positionPiece(white.getPiece(King.class));
        Pieces black = piecesList.get(1);
        System.out.println(black.getPiece(Queen.class));
        System.out.println(white.getPiece(King.class));
    }

    public void add(Pieces pieces) {
        piecesList.add(pieces);
    }
}
