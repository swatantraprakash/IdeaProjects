package TicTacToe.strategies.WinningStrategies;

import TicTacToe.models.Board;
import TicTacToe.models.Move;

import java.util.Map;

public interface WinningStrategy {
    public boolean checkWinner(Board board, Move move);

}
