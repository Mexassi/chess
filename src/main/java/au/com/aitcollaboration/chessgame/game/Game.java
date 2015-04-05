package au.com.aitcollaboration.chessgame.game;

import au.com.aitcollaboration.chessgame.board.Board;
import au.com.aitcollaboration.chessgame.pieces.Pieces;
import au.com.aitcollaboration.chessgame.player.Color;
import au.com.aitcollaboration.chessgame.player.ComputerPlayer;
import au.com.aitcollaboration.chessgame.player.HumanPlayer;
import au.com.aitcollaboration.chessgame.player.Players;
import au.com.aitcollaboration.chessgame.support.In;
import au.com.aitcollaboration.chessgame.support.UIMessages;
import au.com.aitcollaboration.chessgame.support.Utils;

import java.util.LinkedList;
import java.util.List;

public class Game {

    private Board board;
    private Rules rules;
    private Players players;
    private boolean gameOver;
    private List<Board> movesHistory;

    private Game() {
        gameOver = false;
        movesHistory = new LinkedList<Board>();
    }

    public Game(Board board, Rules rules, Players players) {
        this();
        this.board = board;
        this.rules = rules;
        this.players = players;
    }

    public void start() {
        showGreetings();
        playersSetUp();
        showBoard();
        runGame();
    }

    private void showGreetings() {
        System.out.println(UIMessages.GREETINGS);
    }

    private void showBoard() {
        System.out.println(board.display());
    }

    public void playersSetUp() {
        boolean multiPlayers = isMultiPlayers();
        Color color = tossCoin();
        Color flippedColor = color.flip();

        Pieces colorPieces = board.getColoredPieces(color);
        Pieces flippedColorPieces = board.getColoredPieces(flippedColor);

        players.add(new HumanPlayer(getTextAnswer(UIMessages.INSERT_PLAYER_NAME), color, colorPieces));

        if (multiPlayers)
            players.add(new HumanPlayer(getTextAnswer(UIMessages.INSERT_PLAYER_NAME), flippedColor, flippedColorPieces));
        else
            players.add(new ComputerPlayer(flippedColor, flippedColorPieces));
    }

    public boolean isMultiPlayers() {
        return getPlayersNumber() > 1;
    }

    private int getPlayersNumber() {
        while (true) {
            try {
                return getNumericAnswer(UIMessages.SELECT_NUMBER_OF_PLAYERS);
            } catch (NumberFormatException e) {
                // keep looping
            }
        }
    }

    public void runGame() {
        while (!gameOver) {
            rules.getPossibleMovesOn(board);
            players.play();

            //TODO: remove following line and use the one commented
            gameOver = true;
            //gameOver = isGameOver();
        }
    }

    public boolean isGameOver() {
        return rules.isMatchDraw(movesHistory) ||
                rules.isCheckMate(board);
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    private void addMoveToHistory() {
        movesHistory.add(board);
    }

    public Color tossCoin() {
        String coinSide = getTextAnswer(UIMessages.CHOOSE_COIN_SIDE);
        boolean coinMatched = Utils.tossCoin(coinSide);
        return (coinMatched) ? Color.WHITE : Color.BLACK;
    }

    public String getTextAnswer(String question) {
        return In.nextLine(question);
    }

    public int getNumericAnswer(String question) throws NumberFormatException {
        return In.nextInt(question);
    }
}
