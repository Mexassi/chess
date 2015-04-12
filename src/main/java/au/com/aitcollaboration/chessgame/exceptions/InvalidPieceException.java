package au.com.aitcollaboration.chessgame.exceptions;

import au.com.aitcollaboration.chessgame.support.UIMessages;

public class InvalidPieceException extends Throwable {

    public InvalidPieceException() {
        super(UIMessages.INVALID_PIECE_EXCEPTION);
    }
}
