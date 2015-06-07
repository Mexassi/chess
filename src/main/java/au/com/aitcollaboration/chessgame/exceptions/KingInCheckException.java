package au.com.aitcollaboration.chessgame.exceptions;

import au.com.aitcollaboration.chessgame.support.UIMessages;

public class KingInCheckException extends Exception {

    public KingInCheckException(){
        super(UIMessages.KING_IN_CHECK_EXCEPTION);

    }
}