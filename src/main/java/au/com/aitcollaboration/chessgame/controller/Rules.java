package au.com.aitcollaboration.chessgame.controller;

import au.com.aitcollaboration.chessgame.exceptions.PieceNotFoundException;
import au.com.aitcollaboration.chessgame.model.game.structure.Board;
import au.com.aitcollaboration.chessgame.model.game.structure.Square;
import au.com.aitcollaboration.chessgame.model.moves.PieceMoves;
import au.com.aitcollaboration.chessgame.model.moves.PlayerMoves;
import au.com.aitcollaboration.chessgame.model.pieces.King;
import au.com.aitcollaboration.chessgame.model.pieces.Piece;
import au.com.aitcollaboration.chessgame.model.pieces.Pieces;
import au.com.aitcollaboration.chessgame.service.ValidationService;

import java.util.HashMap;
import java.util.Map;

public class Rules {

    private Map<Pieces, PlayerMoves> possibleMoves;
    private ValidationService validationService;

    private Rules() {
        this.possibleMoves = new HashMap<>();
    }

    public Rules(ValidationService validationService) {
        this();
        this.validationService = validationService;
    }

    public boolean isGameOver() {
        return isMatchDraw() || isCheckMate();
    }

    public boolean isCheckMate() {
        return false;
    }

    public boolean isMatchDraw() {
        return false;
    }

    public void findAllPossibleMovesOnBoard(Board board) {
        possibleMoves = board.getAllValidMoves();
    }

    public PlayerMoves getPlayerMoves(Pieces pieces) {
        return possibleMoves.get(pieces);
    }

    public void validatePieceMove(Square fromSquare, Pieces pieces, Board board) throws Exception {
        Piece king = pieces.getPiece(King.class);
        Square kingSquare = board.getCurrentSquare(king);

        Piece currentPiece = fromSquare.getPiece();

        if (currentPiece == null)
            throw new PieceNotFoundException();

        PlayerMoves opponentMoves = getOpponentMoves(pieces);
        PieceMoves currentPieceMoves = currentPiece.getValidMovesOn(board);

        validationService.validateMove(fromSquare, kingSquare, pieces, opponentMoves, currentPieceMoves);

        PlayerMoves playerMoves = getPlayerMoves(pieces);
        playerMoves.add(currentPiece, currentPieceMoves);
    }

    private PlayerMoves getOpponentMoves(Pieces pieces) {
        Map<Pieces, PlayerMoves> playerMovesMap = new HashMap<>(possibleMoves);
        playerMovesMap.remove(pieces);
        return playerMovesMap.values().iterator().next();
    }
}