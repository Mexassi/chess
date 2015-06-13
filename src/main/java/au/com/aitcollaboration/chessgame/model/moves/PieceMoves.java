package au.com.aitcollaboration.chessgame.model.moves;

import au.com.aitcollaboration.chessgame.Color;
import au.com.aitcollaboration.chessgame.model.game.structure.Board;
import au.com.aitcollaboration.chessgame.model.game.structure.Position;
import au.com.aitcollaboration.chessgame.model.game.structure.Square;
import au.com.aitcollaboration.chessgame.model.pieces.Piece;

import java.util.ArrayList;
import java.util.List;

public class PieceMoves {

    private final Square currentSquare;
    private final List<Square> practicalMoves;

    public PieceMoves(Square currentSquare) {
        this.currentSquare = currentSquare;
        this.practicalMoves = new ArrayList<>();
    }

    public void add(Square square) {
        practicalMoves.add(square);
    }

    public boolean isEmpty() {
        return practicalMoves.isEmpty();
    }

    public int size() {
        return practicalMoves.size();
    }

    public boolean contains(Square square) {
        return this.practicalMoves.contains(square);
    }

    public Square getCurrentSquare() {
        return currentSquare;
    }

    public PieceMoves validateMoves(Board board, Square kingSquare) {

        PieceMoves validPieceMoves = new PieceMoves(currentSquare);
        Piece king = kingSquare.getPiece();
        Color currentColor = king.getColor();

        for (Square toSquare : practicalMoves) {

            PlayerMoves opponentPlayerMoves = this.calculateOpponentMoves(toSquare, board, currentColor);

            if (currentSquare.contains(king))
                kingSquare = toSquare;

            if (opponentPlayerMoves.contains(kingSquare))
                continue;

            validPieceMoves.add(toSquare);
        }

        return validPieceMoves;
    }

    PlayerMoves calculateOpponentMoves(Square toSquare, Board board, Color currentColor) {
        Piece tempToPiece = toSquare.getPiece();

        board.doSimulateMovePiece(currentSquare, toSquare);

        PlayerMoves opponentPlayerMoves = board.calculateOpponentPlayerMoves(currentColor);

        board.undoSimulateMovePiece(currentSquare, toSquare, tempToPiece);

        return opponentPlayerMoves;
    }

    @Override
    public String toString() {
        String s = "Valid Moves: ";
        for (Square square : practicalMoves) {
            if (square != null) {
                Position position = square.getPosition();
                s += position.toString();
            }
        }
        return s;
    }
}
