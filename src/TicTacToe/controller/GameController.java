package TicTacToe.controller;

import TicTacToe.Exceptions.BotCountMoreThanOneException;
import TicTacToe.Exceptions.PlayerCountException;
import TicTacToe.Exceptions.SymbolUniquenessException;
import TicTacToe.models.Game;
import TicTacToe.models.GameState;
import TicTacToe.models.Player;
import TicTacToe.strategies.WinningStrategies.WinningStrategy;

import java.util.List;

public class GameController {
    public Game startGame(int dimension, List<Player>players, List<WinningStrategy>winningStrategies) throws BotCountMoreThanOneException, PlayerCountException, SymbolUniquenessException {
        return Game.getBuilder()
                .setDimension(dimension)
                .setPlayers(players)
                .setWinningStrategies(winningStrategies)
                .build();

    }
    public GameState checkGameState(Game game){
        return game.getGameState();
    }
    public void makeMove(Game game){
         game.makeMove();
    }
    public GameState checkGameStatus(Game game){
      return game.getGameState();
    }
    public Player getWinner(Game game){
        return game.getWinner();
    }
    public void printGame(Game game){
        game.printBoard();
    }
}
