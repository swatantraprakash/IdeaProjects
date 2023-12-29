package TicTacToe.models;
import TicTacToe.Exceptions.BotCountMoreThanOneException;
import TicTacToe.Exceptions.PlayerCountException;
import TicTacToe.Exceptions.SymbolUniquenessException;
import TicTacToe.strategies.WinningStrategies.WinningStrategy;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//https://github.com/ankit-arora-1/lld-case-studies/tree/main/src/tictactoe
public class Game {
    private List<Player>players;
    private Board board;
    private List<WinningStrategy>winningStrategies;
    private GameState gameState;
    private List<Move>moves;
    private Player winner;
    private int nextPlayerIndex;
    private int dimension;
    private Game(int dimension,List<Player>players,List<WinningStrategy> winningStrategies){
        this.dimension=dimension;
        this.players=players;
        this.winningStrategies=winningStrategies;
        this.board=new Board(dimension);
        this.moves=new ArrayList<>();
        this.nextPlayerIndex=0;
        this.gameState=GameState.IN_PROGRESS;
    }

    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<WinningStrategy> getWinningStrategies() {
        return winningStrategies;
    }

    public void setWinningStrategies(List<WinningStrategy> winningStrategies) {
        this.winningStrategies = winningStrategies;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public int getNextPlayerIndex() {
        return nextPlayerIndex;
    }

    public void setNextPlayerIndex(int nextPlayerIndex) {
        this.nextPlayerIndex = nextPlayerIndex;
    }
    public static Builder getBuilder(){
        return new Builder();
    }
    public static class Builder{
        private List<Player> players;
        private List<WinningStrategy> winningStrategies;
        private int dimension;

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public Builder setWinningStrategies(List<WinningStrategy> winningStrategy) {
            this.winningStrategies = winningStrategy;
            return this;
        }

        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }
        private void validateBotCount() throws BotCountMoreThanOneException {
            int botSize=0;
            for( Player player:players){
                if(player.getPlayerType().equals(PlayerType.BOT)){
                    botSize++;
                }
                if (botSize>1){
                    throw new BotCountMoreThanOneException();
                }
            }
        }
        private void validatePlayerCountAndDimension() throws PlayerCountException{
          if(players.size()!=dimension-1){
              throw new PlayerCountException();
          }
        }
        private void validateSymbolUniqueness() throws SymbolUniquenessException{
            Map<Character,Integer> symbolCount=new HashMap<>();
            for(Player player:players){
                if(!symbolCount.containsKey(player.getSymbol().getaChar())){
                    symbolCount.put(player.getSymbol().getaChar(), 1);
                }else {
                    symbolCount.put(player.getSymbol().getaChar(),player.getSymbol().getaChar()+1);
                }
                if (symbolCount.get(player.getSymbol().getaChar())>1){
                    throw new SymbolUniquenessException();
                }
            }
        }
        public void validate() throws BotCountMoreThanOneException, PlayerCountException, SymbolUniquenessException {
            validateBotCount();
            validatePlayerCountAndDimension();
            validateSymbolUniqueness();

        }

        public Game build() throws BotCountMoreThanOneException, PlayerCountException, SymbolUniquenessException {
            validate();
            return new Game(dimension,players,winningStrategies);
        }
    }
    private boolean validateMove(Move move){
        int row=move.getCell().getRow();
        int col=move.getCell().getCol();
        if(col>board.getDimension()||row>board.getDimension()){
            return false;
        }
        if(!board.getBoard().get(row).get(col).getCellState().equals(CellState.EMPTY)){
            return false;
        }
        return true;
    }
    public void makeMove() {
        Player currentMovePlayer = players.get(nextPlayerIndex);
        System.out.println("It is " + currentMovePlayer.getName() + "'s turn");
        Move currentPlayerMove = currentMovePlayer.makeMove(board);
        if (!validateMove(currentPlayerMove)) {
            System.out.println("Invalid Move.Please try again!!!");
            return;
        }
        int row = currentPlayerMove.getCell().getRow();
        int col = currentPlayerMove.getCell().getCol();
        Cell cellToChange = board.getBoard().get(row).get(col);
        cellToChange.setCellState(CellState.FILLED);
        cellToChange.setPlayer(currentMovePlayer);


        Move finalMoveObj = new Move(cellToChange, currentMovePlayer);
        moves.add(finalMoveObj);


        nextPlayerIndex += 1;
        nextPlayerIndex %= players.size();
        if(checkWinner(board,finalMoveObj)){
            gameState=GameState.WIN;
            winner=currentMovePlayer;
        } else if (moves.size()==this.board.getDimension()*this.board.getDimension()) {
           gameState=GameState.DRAWN;
        }
    }
    public boolean checkWinner(Board board,Move move){
        for(WinningStrategy winningStrategy:winningStrategies){
            if(winningStrategy.checkWinner(board,move)){
                return true;
            }
            return false;
        }
        return false;
    }
        public void printBoard(){
            board.printBoard();
        }

}
