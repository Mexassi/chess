package au.com.aitcollaboration.chessgame.pieces;

import au.com.aitcollaboration.chessgame.player.Color;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Pieces {

    private List<Piece> currentPieces;
    private List<Piece> lostPieces;

    public final static int BISHOP_QTY = 2;
    public final static int KNIGHT_QTY = 2;
    public final static int ROOK_QTY = 2;
    public final static int PAWN_QTY = 8;

    public Pieces(Color color) {
        this.currentPieces = new ArrayList<Piece>();
        this.lostPieces = new ArrayList<Piece>();
        createPieceSet(color);
    }

    private void createPieceSet(Color color) {
        currentPieces.add(new King(color));
        currentPieces.add(new Queen(color));

        for (int i = 0; i < BISHOP_QTY; i++) {
            currentPieces.add(new Bishop(color));
        }

        for (int i = 0; i < KNIGHT_QTY; i++) {
            currentPieces.add(new Knight(color));
        }

        for (int i = 0; i < ROOK_QTY; i++) {
            currentPieces.add(new Rook(color));
        }

        for (int i = 0; i < PAWN_QTY; i++) {
            currentPieces.add(new Pawn(color));
        }

        System.out.println(currentPieces.size());
    }

    public Piece getPiece(Class pieceClass) {
        Iterator<Piece> pieceIterator = currentPieces.iterator();

        while (pieceIterator.hasNext()) {
            Piece piece = pieceIterator.next();
            if (pieceClass.equals(piece.getClass())) {
                pieceIterator.remove();
                return piece;
            }

        }
        return null;

    }
}
