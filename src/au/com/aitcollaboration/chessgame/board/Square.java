package au.com.aitcollaboration.chessgame.board;

import au.com.aitcollaboration.chessgame.pieces.Piece;

/**
 * Created by Massimo on 18/01/2015.
 */
public class Square {

    private Piece piece;
    private int myX;
    private int myY;

    public Square(){
    }

    public boolean isAvailable(){
        return this.piece == null;
    }

    public Square placeMyX(int myX){
        this.myX = myX;
        return this;
    }

    public Square placeMyY(int myY){
        this.myY = myY;
        return this;
    }
}