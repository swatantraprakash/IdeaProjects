import TicTacToe.Exceptions.BotCountMoreThanOneException;
import TicTacToe.Exceptions.PlayerCountException;
import TicTacToe.Exceptions.SymbolUniquenessException;
import TicTacToe.controller.GameController;
import TicTacToe.models.*;
import TicTacToe.strategies.WinningStrategies.ColumnWinningStrategy;
import TicTacToe.strategies.WinningStrategies.DiagonalWinningStrategy;
import TicTacToe.strategies.WinningStrategies.RowWinningStrategy;
import TicTacToe.strategies.WinningStrategies.WinningStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws BotCountMoreThanOneException, PlayerCountException, SymbolUniquenessException {
        System.out.println("Hello world!");
        GameController gameController=new GameController();
        Scanner scanner=new Scanner(System.in);
        int gameDimension=3;
        List<Player>players=new ArrayList<>();
        players.add(new Player(1L,"Swatantra",new Symbol('X'), PlayerType.HUMAN));
        players.add(new Bot(2L,"GPT",new Symbol('O'),BotDifficultyLevel.EASY));

        List<WinningStrategy>winningStrategies=new ArrayList<>();
        //winningStrategies.add(new RowWinningStrategy());
        winningStrategies.add(new ColumnWinningStrategy());
        //winningStrategies.add(new DiagonalWinningStrategy());
        Game game = gameController.startGame(gameDimension, players, winningStrategies);
        while (gameController.checkGameState(game).equals(GameState.IN_PROGRESS)){
            gameController.printGame(game);
            gameController.makeMove(game);
        }
        game.getBoard().printBoard();
        if(gameController.checkGameStatus(game).equals(GameState.WIN)){
            System.out.println("Hurrah !!! Winner is "+gameController.getWinner(game).getName());
        }else if(gameController.checkGameStatus(game).equals(GameState.DRAWN)){
            System.out.println("Drawn!!");
        }
    }
}